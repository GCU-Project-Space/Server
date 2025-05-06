package gcu.web.paymentservice.platform.adapter.in.web;

import gcu.web.paymentservice.common.response.ApiResponse;
import gcu.web.paymentservice.platform.adapter.in.web.dto.request.CancelPaymentRequest;
import gcu.web.paymentservice.platform.adapter.in.web.dto.request.ConfirmPaymentRequest;
import gcu.web.paymentservice.platform.adapter.in.web.dto.response.PaymentResponse;
import gcu.web.paymentservice.platform.application.in.PaymentUseCase;
import gcu.web.paymentservice.platform.domain.Payment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentApi {

    private final PaymentUseCase paymentService;

    /// 리액트에서 결제한 내용 얻기
    @PostMapping("/confirm")
    public ApiResponse<PaymentResponse> createPayment(@RequestBody ConfirmPaymentRequest request) throws Exception {

        log.info("createPayment called with paymentKey: {}", request.paymentKey());

        // 토스에게 결제 승인 요청
        Payment payment = paymentService.savePayment(request);


        return ApiResponse.created(PaymentResponse.from(payment), "결제가 정상적으로 성공되었습니다.");
    }

     /// 결제 취소 요청
    public ApiResponse<String> cancelPayment(@RequestBody CancelPaymentRequest request) throws IOException, InterruptedException {

        return null;
    }

}
