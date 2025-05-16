package gcu.web.paymentservice.platform.application.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import gcu.web.paymentservice.common.response.ErrorCode;
import gcu.web.paymentservice.platform.adapter.in.web.dto.request.CancelPaymentRequest;
import gcu.web.paymentservice.platform.adapter.in.web.dto.request.ConfirmPaymentRequest;
import gcu.web.paymentservice.platform.adapter.out.external.order.OrderFeignPort;
import gcu.web.paymentservice.platform.application.in.PaymentUseCase;
import gcu.web.paymentservice.platform.application.out.pg.PaymentExternalPort;
import gcu.web.paymentservice.platform.application.out.PaymentPort;
import gcu.web.paymentservice.platform.domain.Payment;
import gcu.web.paymentservice.platform.domain.PaymentStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.http.HttpResponse;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PaymentService implements PaymentUseCase {

    private final ObjectMapper objectMapper;

    // DB에 저장
    private final PaymentPort paymentPort;

    // 외부 토스 의존
    private final PaymentExternalPort pgPort;

    // 주문 서비스 연동
    private final OrderFeignPort orderFeignPort;


    // 리액트에게 결과 리턴
    @Override
    public Payment savePayment(ConfirmPaymentRequest confirmPaymentRequest) throws Exception {

        /// 토스에게 요청
        HttpResponse<String> response = pgPort.requestConfirm(confirmPaymentRequest);
        JsonNode responseBody = objectMapper.readTree(response.body());

        log.info("response body: {}", responseBody.toString());

        /// 응답값 DB에 저장
        if (response.statusCode() == 200) {
            // 객체 저장
            Payment payment = createPayment(confirmPaymentRequest, responseBody);

            Payment saved = paymentPort.savePayment(payment);

            // 주문 정보 전달
            orderFeignPort.completeOrder(saved.getOrderId());

            return saved;

        } else {
            log.error("response status code: {}", response.statusCode());
            throw new IllegalStateException(ErrorCode.BAD_REQUEST.getMessage());
        }
    }

    // 내 결제 목록 가져오기
    @Override
    public Page<Payment> findMyPayments(Long memberId, Pageable pageable) {
        return null;
    }

    @Override
    public void deletePayment(CancelPaymentRequest request) throws IOException, InterruptedException {

        /// 존재 예외처리
        Payment payment = paymentPort.loadPaymentByPaymentKey(request.paymentKey())
                .orElseThrow(() -> new IllegalStateException(ErrorCode.NOT_FOUND_EXIST_PAYMENT.getMessage()));

        /// 토스에게 요청
        HttpResponse<String> response = pgPort.requestPaymentCancel(request.paymentKey(), request.cancelReason());
        JsonNode responseBody = objectMapper.readTree(response.body());

        log.info("response body: {}", responseBody.toString());

        /// 응답값 DB에 저장
        if (response.statusCode() == 200) {

            // 객체 삭제
            paymentPort.deletePayment(payment.getId());

            // 주문 취소 정보 전달
            orderFeignPort.cancelOrder(payment.getOrderId());

        } else {
            log.error("response status code: {}", response.statusCode());
            throw new IllegalStateException(ErrorCode.BAD_REQUEST.getMessage());
        }

    }

    /// 내부 메서드
    private static Payment createPayment(ConfirmPaymentRequest confirmPaymentRequest, JsonNode responseBody) {
        return Payment.builder()
                .paymentKey(responseBody.get("paymentKey").asText())
                .orderId(confirmPaymentRequest.orderId())
                .paymentMethod(responseBody.get("method").asText())
                .amount(responseBody.get("totalAmount").asInt())
                .orderName(responseBody.get("orderName").asText())
                .status(PaymentStatus.PAID)
                .createdAt(responseBody.get("requestedAt").asText())
                .build();
    }

}
