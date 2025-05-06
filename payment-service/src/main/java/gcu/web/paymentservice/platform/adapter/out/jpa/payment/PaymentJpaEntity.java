package gcu.web.paymentservice.platform.adapter.out.jpa.payment;

import gcu.web.paymentservice.platform.domain.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

    private LocalDateTime createdAt;

}
