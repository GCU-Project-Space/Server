package gcu.web.paymentservice.platform.adapter.in.web.dto.request;

public record CancelPaymentRequest(
        String paymentKey, String cancelReason) {
}
