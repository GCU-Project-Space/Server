package gcu.web.paymentservice.platform.adapter.in.web.dto.response;

import gcu.web.paymentservice.platform.domain.Payment;
import lombok.Builder;

@Builder
public record PaymentResponse(
        Long paymentId,
        String paymentKey,
        String orderId,
        String method,
        String orderName,
        String status,
        int totalAmount,
        String requestedAt
) {

    public static PaymentResponse from(Payment payment) {
        return PaymentResponse.builder()
                .paymentId(payment.getId())
                .paymentKey(payment.getPaymentKey())
                .orderId(payment.getOrderId())
                .method(payment.getPaymentMethod())
                .orderName(payment.getOrderName())
                .status(payment.getStatus().toString())
                .totalAmount(payment.getAmount())
                .requestedAt(payment.getCreatedAt())
                .build();
    }

}

