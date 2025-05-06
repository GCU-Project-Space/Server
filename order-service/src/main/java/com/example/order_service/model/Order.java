package com.example.order_service.model;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Order {

    private Long id;

    private Long recruitmentId;

    private Long userId;

    private int totalPrice;

    private LocalDateTime createdAt;

    private List<OrderItem> orderItems;

    private OrderStatus orderStatus;
    
}
