package gcu.web.paymentservice.platform.application.in;

import gcu.web.paymentservice.platform.adapter.in.web.dto.request.ConfirmPaymentRequest;
import gcu.web.paymentservice.platform.adapter.in.web.dto.request.PaymentRequest;
import gcu.web.paymentservice.platform.domain.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.net.http.HttpResponse;

public interface PaymentUseCase {

    /*
        결제 진행	- 방 참가를 위한 결제 진행 - 토스페이 API를 활용하여 결제를 요청합니다.
        결제 완료	- 결제 완료 - 토스페이를 통해 결제를 완료합니다.
        결제 환불	- 미달로 인한 결제 환불 - 최소 주문금액이 모집되지않았기에 결제가 환불 됩니다.
        결제 환불	- 취소로 인한 결제 환불 - 방장이 취소하였기에 결제가 환불 됩니다.
        나의 결제 - 목록 확인 - 결제 목록에서 [결제 시간, 금액, 주문 가게, 시킨 음식] 의 정보를 확인할 수 있습니다.
     */

    /// 결제 생성
    // 리액트에게 결과 리턴
    Payment savePayment(ConfirmPaymentRequest confirmPaymentRequest , HttpResponse<String> response) throws Exception;

    /// 결제 조회
    // 내 결제 목록 가져오기
    Page<Payment> findMyPayments(Long memberId, Pageable pageable);

    /// 결제 취소
    void deletePayment(Long userId, Long paymentId);

}
