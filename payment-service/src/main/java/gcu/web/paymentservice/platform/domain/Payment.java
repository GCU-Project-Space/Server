package gcu.web.paymentservice.platform.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Payment {

    private Long id;

    private String orderId;

    private String orderName;

    private String paymentMethod;

    private String paymentKey;

    private int amount;

    private PaymentStatus status;

    private String createdAt;


    public static Payment of(String orderId, String orderName, String paymentMethod, String paymentKey, int amount, String createdAt, PaymentStatus status) {
        return Payment.builder()
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
