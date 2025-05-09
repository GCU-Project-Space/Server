package gcu.web.paymentservice.platform.adapter.in.web.dto.request;

public record ConfirmPaymentRequest(
        String paymentKey, String orderId, int amount
) {
}
