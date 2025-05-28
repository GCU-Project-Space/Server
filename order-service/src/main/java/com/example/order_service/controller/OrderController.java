package com.example.order_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.order_service.common.ResponseBody;
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

    // Create
    // 개인 주문 추가
    @PostMapping
    public ResponseEntity<ResponseBody<OrderResponse>> addOrder(@RequestBody OrderRequest order) {

        OrderResponse response = orderService.createOrder(order);

        return ResponseEntity.ok(ResponseBody.ok(response, "주문이 추가되었습니다."));
    }

    // Read
    // 주문 상세 조회
    @GetMapping("/detail/{orderId}")
    public ResponseEntity<ResponseBody<OrderResponse>> getOrderDetail(@PathVariable Long orderId) {
        
        OrderResponse response = orderService.getOrder(orderId);
        
        return ResponseEntity.ok(ResponseBody.ok(response, Long.toString(orderId) + "의 주문 상세입니다."));
    }

    // 주문 내역 조회
    @GetMapping("/{userId}")
    public ResponseEntity<ResponseBody<List<OrderResponse>>> getUserOrders(@PathVariable Long userId) {

        List<OrderResponse> response = orderService.getOrdersByUserId(userId);

        return ResponseEntity.ok(ResponseBody.ok(response, Long.toString(userId) + "님의 주문 내역입입니다."));
    }

    // 가게 주문 내역 조회
    @GetMapping("/stores/{storeId}")
    public ResponseEntity<ResponseBody<List<OrderResponse>>> getStoreOrders(@PathVariable Long storeId) {

        List<OrderResponse> response = orderService.getCurrentOrdersByStoreId(storeId);

        return ResponseEntity.ok(ResponseBody.ok(response, Long.toString(storeId) + "의 주문 내역입니다."));
    }

    // Update
    // 주문 전송
    @PutMapping("/{orderId}/submit")
    public ResponseEntity<ResponseBody<OrderResponse>> submitOrder(@PathVariable Long orderId) {
        OrderResponse response = orderService.payOrder(orderId);

        return ResponseEntity.ok(ResponseBody.ok(response, Long.toString(orderId) + "의 주문 내역입니다."));
    }

    // Delete
    // 단체 주문 취소
    @PutMapping("/recruitments/{recruitmentId}/cancel")
    public ResponseEntity<ResponseBody<Void>> cancelGroupOrder(@PathVariable Long recruitmentId) {

        orderService.cancelOrdersByGroupId(recruitmentId);

        return ResponseEntity.ok(ResponseBody.deleted("단체 주문이 취소되었습니다."));
    }

    // 개인 주문 취소
    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<ResponseBody<Void>> cancelOrder(@PathVariable Long orderId) {

        orderService.cancelOrder(orderId);

        return ResponseEntity.ok(ResponseBody.deleted("주문이 취소되었습니다."));
    }

}
