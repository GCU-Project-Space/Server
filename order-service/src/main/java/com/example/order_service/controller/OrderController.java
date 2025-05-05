package com.example.order_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.order_service.dto.Dto;
import com.example.order_service.entity.Order;
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
    public ResponseEntity<String> addOrder(@RequestBody Object request) {
        return ResponseEntity.ok("개인 주문 추가: " + request);
    }

    // 개인 주문 취소
    @PatchMapping("/{orderId}/cancel")
    public ResponseEntity<String> cancelOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok("개인 주문 취소: " + orderId);
    }

    // 주문 상세 조회
    @GetMapping("/detail/{orderId}")
    public ResponseEntity<String> getOrderDetail(@PathVariable Long orderId) {
        return ResponseEntity.ok("주문 상세 조회: " + orderId);
    }

    // 주문 내역 조회
    @GetMapping("/{userId}")
    public ResponseEntity<String> getUserOrders(@PathVariable Long userId) {
        return ResponseEntity.ok("주문 내역 조회: " + userId);
    }

    // 단체 주문 전송
    @PostMapping("/submit/{recruitmentId}")
    public ResponseEntity<String> submitGroupOrder(@PathVariable Long recruitmentId) {
        return ResponseEntity.ok("단체 주문 전송: " + recruitmentId);
    }

    // 단체 주문 취소
    @PatchMapping("/recruitments/{recruitmentId}/cancel")
    public ResponseEntity<String> cancelGroupOrder(@PathVariable Long recruitmentId) {
        return ResponseEntity.ok("단체 주문 취소: " + recruitmentId);
    }

    // 가게 주문 상세 조회
    @GetMapping("/stores/detail/{orderId}")
    public ResponseEntity<String> getStoreOrderDetail(@PathVariable Long orderId) {
        return ResponseEntity.ok("가게 주문 상세 조회: " + orderId);
    }

    // 가게 주문 내역 조회
    @GetMapping("/stores/{storeId}")
    public ResponseEntity<String> getStoreOrders(@PathVariable Long storeId) {
        return ResponseEntity.ok("가게 주문 내역 조회: " + storeId);
    }

    // 가게 주문 수락
    @PutMapping("/{orderId}/accept")
    public ResponseEntity<String> acceptOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok("가게 주문 수락: " + orderId);
    }

    // 가게 주문 거절
    @PutMapping("/{orderId}/reject")
    public ResponseEntity<String> rejectOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok("가게 주문 거절: " + orderId);
    }

    // 가게 배달 시작
    @PutMapping("/{orderId}/start-delivery")
    public ResponseEntity<String> startDelivery(@PathVariable Long orderId) {
        return ResponseEntity.ok("가게 배달 시작: " + orderId);
    }

    // 가게 배달 완료
    @PutMapping("/{orderId}/complete")
    public ResponseEntity<String> completeDelivery(@PathVariable Long orderId) {
        return ResponseEntity.ok("가게 배달 완료: " + orderId);
    }

    // 가게 배달 실패
    @PutMapping("/{orderId}/fail")
    public ResponseEntity<String> failDelivery(@PathVariable Long orderId) {
        return ResponseEntity.ok("가게 배달 실패: " + orderId);
    }
}
