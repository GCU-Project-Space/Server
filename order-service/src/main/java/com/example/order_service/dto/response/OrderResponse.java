package com.example.order_service.dto.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.order_service.common.OrderStatus;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(example = "1")
    private Long orderId;

    @Schema(example = "1")
    private Long groupId;

    @Schema(example = "1")
    private Long userId;

    @Schema(example = "1")
    private Long storeId;

    @Schema(example = "100000")
    private int totalPrice;

    @Schema(example = "2023-10-27 (금) 15:30:45.123456789")
    private LocalDateTime createdAt;

    @Builder.Default
    private List<MenuResponse> menus = new ArrayList<>();

    @Builder.Default
    private OrderStatus status = OrderStatus.PREPAID;

}
