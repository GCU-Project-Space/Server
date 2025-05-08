package com.example.order_service.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.order_service.common.OrderStatus;
import com.example.order_service.dto.response.OrderResponse;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class OrderEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long groupId;

    private Long userId;

    private Long storeId;

    private int totalPrice;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<MenuSnapshot> menus = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private OrderStatus status = OrderStatus.PREPAID;

    public OrderResponse toResponse() {
        return OrderResponse.builder()
        .orderId(getId())
        .groupId(getGroupId())
        .userId(getUserId())
        .storeId(getStoreId())
        .totalPrice(getTotalPrice())
        .createdAt(getCreatedAt())
        .menus(getMenus().stream()
            .map(item -> item.toResponse())
            .collect(Collectors.toList())
        )
        .status(getStatus())
        .build();
    }

    public void calculateTotalPrice() {
        this.totalPrice = menus.stream()
            .mapToInt(menu -> menu.calculateTotalPrice()) // MenuSnapshot::calculateTotalPrice 했을 때 왜 에러가 날까까
            .sum();
    }
    
    public void cancel() {
        this.status = OrderStatus.CANCELED;
    }

    public void pay() {
        this.status = OrderStatus.PAID;
    }

}
