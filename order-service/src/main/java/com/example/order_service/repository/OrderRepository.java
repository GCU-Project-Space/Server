package com.example.order_service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.order_service.entity.OrderEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query("SELECT o FROM OrderEntity o WHERE userId = :userId")
    Optional<List<OrderEntity>> findByUserId(@Param("userId") Long userId);

    @Query("SELECT o FROM OrderEntity o WHERE groupId = :groupId")
    Optional<List<OrderEntity>> findByGroupId(@Param("groupId") Long groupId);

    @Query("SELECT o FROM OrderEntity o WHERE storeId = :storeId")
    Optional<List<OrderEntity>> findByStoreId(@Param("storeId") Long storeId);
    
}
