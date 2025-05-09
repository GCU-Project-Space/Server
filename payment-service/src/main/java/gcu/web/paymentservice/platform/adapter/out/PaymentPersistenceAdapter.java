package gcu.web.paymentservice.platform.adapter.out;

import gcu.web.paymentservice.platform.adapter.out.jpa.payment.PaymentJpaEntity;
import gcu.web.paymentservice.platform.adapter.out.jpa.payment.PaymentJpaRepository;
import gcu.web.paymentservice.platform.application.out.PaymentPort;
import gcu.web.paymentservice.platform.domain.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PaymentPersistenceAdapter implements PaymentPort {

    private final PaymentJpaRepository repository;

    @Override
    public Payment savePayment(Payment payment) {
        var entity = PaymentJpaEntity.from(payment);

        return repository.save(entity)
                .toDomain();
    }

    // 결제 상세 조회
    @Override
    public Optional<Payment> loadPayment(Long id) {

        return repository.findById(id)
                .map(PaymentJpaEntity::toDomain);
    }

    @Override
    public Optional<Payment> loadPaymentByPaymentKey(String paymentKey) {
        return repository.findByPaymentKey(paymentKey)
                .map(PaymentJpaEntity::toDomain);
    }

    @Override
    public Page<Payment> loadPaymentsByUserId(Long userId, Pageable pageable) {
        return null;
    }

    @Override
    public void deletePayment(Long paymentId) {
        repository.deleteById(paymentId);
    }
}
