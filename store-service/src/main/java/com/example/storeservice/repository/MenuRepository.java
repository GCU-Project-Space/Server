package com.example.storeservice.repository;

import com.example.storeservice.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    boolean existsByStoreIdAndName(Long storeId, String name);

}
