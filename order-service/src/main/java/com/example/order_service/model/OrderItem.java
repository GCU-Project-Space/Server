package com.example.order_service.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class OrderItem {

    private Long menuId;

    private String menuName;

    private int basePrice;

    private int count;

    private int totalPrice;

    private List<OrderItemOption> options; 
    
}
