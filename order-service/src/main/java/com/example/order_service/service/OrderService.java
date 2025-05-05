package com.example.order_service.service;

import com.example.order_service.repository.OrderRepository;

public class OrderService{
    private final OrderRepository orderRepository;
    
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    
}
