package com.example.order_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.order_service.entity.OrderEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findByUserId(Long userId); // 반환형이 List<T>라면 JPA는 null이 아닌 빈 리스트를 반환한다.

    List<OrderEntity> findByGroupId(Long groupId);

    List<OrderEntity> findByStoreId(Long storeId);
    
}
