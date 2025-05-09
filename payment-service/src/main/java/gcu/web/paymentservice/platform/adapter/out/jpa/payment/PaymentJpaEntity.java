package gcu.web.paymentservice.platform.adapter.out.jpa.payment;

import gcu.web.paymentservice.platform.domain.Payment;
import gcu.web.paymentservice.platform.domain.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class PaymentJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderId;

    private String orderName;

    private String paymentMethod;

    private String paymentKey;

    private int amount;

    private PaymentStatus status;

    private String createdAt;


    /// From
    public static PaymentJpaEntity from(Payment payment) {
        return PaymentJpaEntity.builder()
                .orderId(payment.getOrderId())
                .orderName(payment.getOrderName())
                .paymentMethod(payment.getPaymentMethod())
                .paymentKey(payment.getPaymentKey())
                .amount(payment.getAmount())
                .status(payment.getStatus())
                .createdAt(payment.getCreatedAt())
                .build();
    }

    ///  ToDomain
    public Payment toDomain() {
        return Payment.builder()
                .id(id)
                .orderId(orderId)
                .orderName(orderName)
                .paymentMethod(paymentMethod)
                .paymentKey(paymentKey)
                .amount(amount)
                .status(status)
                .createdAt(createdAt)
                .build();

    }
}
