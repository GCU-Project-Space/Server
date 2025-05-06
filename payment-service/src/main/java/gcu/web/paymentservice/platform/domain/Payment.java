package gcu.web.paymentservice.platform.domain;

import java.time.LocalDateTime;

public class Payment {

    private Long id;

    private String orderId;

    private String orderName;

    private String paymentMethod;

    private String paymentKey;

    private int amount;

    private PaymentStatus status;

    private LocalDateTime createdAt;

}
