package gcu.web.paymentservice.platform.adapter.in.web.dto.response;

import gcu.web.paymentservice.platform.domain.Payment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record PaymentResponse(
        @Schema(example = "1")
        Long paymentId,

        @Schema(example = "tgen_20250520130928ZcAV7")
        String paymentKey,

        @Schema(example = "ORDER-08f1fc98-dbc5-4859-b77f-bacfd97aaf2c")
        String orderId,

        @Schema(example = "간편결제")
        String method,

        @Schema(example = "VIP회원권 외 2건")
        String orderName,

        @Schema(example = "DONE")
        String status,

        @Schema(example = "10000")
        int totalAmount,

        @Schema(example = "2025-05-20T13:09:28+09:00")
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

