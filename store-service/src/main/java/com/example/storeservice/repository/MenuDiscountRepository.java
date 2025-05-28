package com.example.storeservice.repository;

import com.example.storeservice.entity.MenuDiscount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MenuDiscountRepository extends JpaRepository<MenuDiscount, Long> {
    Optional<MenuDiscount> findByMenuId(Long menuId);
    boolean existsByMenuId(Long menuId);
    void deleteByMenuId(Long menuId);
}
