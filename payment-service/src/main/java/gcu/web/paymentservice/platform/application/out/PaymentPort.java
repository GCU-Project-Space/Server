package gcu.web.paymentservice.platform.application.out;

import gcu.web.paymentservice.platform.domain.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PaymentPort {

    /// 결제 저장
    Payment savePayment(Payment payment);

    /// 결제 조회
    // 결제 상세 조회
    Optional<Payment> loadPayment(Long id);

    // 유저 아이디 바탕 결제 조회
    Page<Payment> loadPaymentsByUserId(Long userId, Pageable pageable);

    // 결제 삭제
    void deletePayment(String paymentId);
}
