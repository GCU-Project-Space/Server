package com.example.order_service.entity;

import java.time.LocalDateTime;

import com.example.order_service.model.OrderStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    private Long recruitmentId;

    private Long userId;

    private int totalPrice;

    private OrderStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime deletedAt;
}
