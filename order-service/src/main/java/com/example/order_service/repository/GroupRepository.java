package com.example.order_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.order_service.entity.GroupEntity;

public interface GroupRepository extends JpaRepository<GroupEntity, Long>{

    List<GroupEntity> findByCategory(String category);

    @Query("SELECT DISTINCT g FROM GroupEntity g WHERE g.id IN (SELECT o.groupId FROM OrderEntity o WHERE o.userId = :userId)")
    List<GroupEntity> findGroupsByUserId(@Param("userId") Long userId);

    List<GroupEntity> findByStoreId(Long storeId);
    
}
