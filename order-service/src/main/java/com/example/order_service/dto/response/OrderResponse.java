package com.example.order_service.dto.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.order_service.common.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class OrderResponse {
    
    private Long orderId;

    private Long groupId;

    private Long userId;

    private Long storeId;

    private int totalPrice;

    private LocalDateTime createdAt;

    @Builder.Default
    private List<MenuResponse> menus = new ArrayList<>();

    @Builder.Default
    private OrderStatus status = OrderStatus.PREPAID;

}
