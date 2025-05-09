package com.example.order_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.order_service.common.OrderStatus;
import com.example.order_service.dto.request.OrderRequest;
import com.example.order_service.dto.response.OrderResponse;
import com.example.order_service.entity.OrderEntity;
import com.example.order_service.repository.OrderRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderService{
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /*
     * 오더 CRUD 정의
     * 
     * C
     * - 개인 주문 추가
     * 
     * R
     * - 개인 주문 조회
     * - 단체 주문 조회
     * - 가게 현재 주문 내역 조회
     * - 가게 전체 주문 내역 조회
     * U
     * - 개인 주문 변경
     * 
     * D (소프트 딜리트)
     * - 개인 주문 취소
     * - 단체 주문 취소
     */
    
    // Create
    @Transactional
    public OrderResponse createOrder(OrderRequest order) {
        // 1. Order 엔티티 생성
        OrderEntity orderEntity = order.toEntity();
        
        // 2. 파생 속성 계산
        orderEntity.calculateTotalPrice();

        // 3. 저장 - cascade로 인해 연관 엔티티들도 자동 저장
        orderRepository.save(orderEntity);

        return orderEntity.toResponse();
    }

    // Read
    public OrderResponse getOrder(Long orderId) {
        return orderRepository.findById(orderId)
        .orElseThrow(() -> new RuntimeException("Order not found"))
        .toResponse();
    }

    public List<OrderResponse> getOrdersByUserId(Long userId) {
        List<OrderEntity> orderEntities = orderRepository.findByUserId(userId)
        .orElseThrow(() -> new RuntimeException("User not found"));

        return orderEntities.stream().map(order -> order.toResponse()).collect(Collectors.toList());
    }

    public List<OrderResponse> getOrdersByGroupId(Long groupId) {
        List<OrderEntity> orderEntities = orderRepository.findByGroupId(groupId)
        .orElseThrow(() -> new RuntimeException("Group not found"));

        return orderEntities.stream().map(order -> order.toResponse()).collect(Collectors.toList());
    }

    // Update
    @Transactional
    public OrderResponse changeOrder(Long orderId, OrderRequest orderRequest) {
        // 1. 기존 주문 조회
        OrderEntity existingOrder = orderRepository.findById(orderId)
            .orElseThrow(() -> new RuntimeException("Order not found"));
        
        // 2. 기존 메뉴 및 옵션 제거 (고아 객체 제거)
        existingOrder.getMenus().clear();
        
        // 3. 새로운 주문 정보로 업데이트
        OrderEntity newOrder = orderRequest.toEntity();
        
        // 4. 변경 불가능한 필드 유지
        newOrder.setId(orderId);
        newOrder.setCreatedAt(existingOrder.getCreatedAt());
        
        // 5. 파생 속성 재계산
        newOrder.calculateTotalPrice();
        
        // 6. 변경사항 적용
        return orderRepository.save(newOrder).toResponse();
    }

    public OrderResponse payOrder(Long orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId)
        .orElseThrow(() -> new RuntimeException("Order not found"));
        
        orderEntity.pay();

        return orderRepository.save(orderEntity).toResponse();
    }

    // Delete
    public void cancelOrder(Long orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId)
        .orElseThrow(() -> new RuntimeException("Order not found"));
        
        orderEntity.cancel();

        orderRepository.save(orderEntity);
    }

    public void cancelOrdersByGroupId(Long groupId) {
        List<OrderEntity> orderEntities = orderRepository.findByGroupId(groupId)
        .orElseThrow(() -> new RuntimeException("Group not found"));

        orderRepository.saveAll(orderEntities.stream()
            .map(order -> {
                order.cancel();
                return order; 
            }).collect(Collectors.toList())
        );
    }

    public List<OrderResponse> getOrdersByStoreId(Long storeId) {
        return orderRepository.findByStoreId(storeId)
        .orElseThrow(() -> new RuntimeException("Store not found"))
        .stream().map(order -> order.toResponse()).collect(Collectors.toList());
    }

    public List<OrderResponse> getCurrentOrdersByStoreId(Long storeId) {
        return orderRepository.findByStoreId(storeId)
        .orElseThrow(() -> new RuntimeException("Store not found"))
        .stream().filter(order -> order.getStatus() == OrderStatus.PAID).map(order -> order.toResponse()).collect(Collectors.toList());
    }

}
