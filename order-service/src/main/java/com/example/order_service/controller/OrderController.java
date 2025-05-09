package com.example.order_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.order_service.dto.request.OrderRequest;
import com.example.order_service.dto.response.OrderResponse;
import com.example.order_service.service.OrderService;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // 개인 주문 추가
    @PostMapping
    public ResponseEntity<OrderResponse> addOrder(@RequestBody OrderRequest order) {

        OrderResponse response = orderService.createOrder(order);

        return ResponseEntity.ok(response);
    }

    // 개인 주문 취소
    @PatchMapping("/{orderId}/cancel")
    public ResponseEntity<String> cancelOrder(@PathVariable Long orderId) {

        orderService.cancelOrder(orderId);

        return ResponseEntity.ok("개인 주문 취소: " + orderId);
    }

    // 주문 상세 조회
    @GetMapping("/detail/{orderId}")
    public ResponseEntity<OrderResponse> getOrderDetail(@PathVariable Long orderId) {
        
        OrderResponse resposne = orderService.getOrder(orderId);
        
        return ResponseEntity.ok(resposne);
    }

    // 주문 내역 조회
    @GetMapping("/{userId}")
    public ResponseEntity<List<OrderResponse>> getUserOrders(@PathVariable Long userId) {

        List<OrderResponse> response = orderService.getOrdersByUserId(userId);

        return ResponseEntity.ok(response);
    }

    // 단체 주문 취소
    @PatchMapping("/recruitments/{recruitmentId}/cancel")
    public ResponseEntity<String> cancelGroupOrder(@PathVariable Long groupId) {

        orderService.cancelOrdersByGroupId(groupId);

        return ResponseEntity.ok("단체 주문 취소: " + groupId);
    }

    // 가게 주문 내역 조회
    @GetMapping("/stores/{storeId}")
    public ResponseEntity<List<OrderResponse>> getStoreOrders(@PathVariable Long storeId) {

        List<OrderResponse> response = orderService.getCurrentOrdersByStoreId(storeId);

        return ResponseEntity.ok(response);
    }

}
