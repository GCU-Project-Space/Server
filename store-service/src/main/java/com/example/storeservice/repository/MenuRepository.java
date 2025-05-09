package com.example.storeservice.repository;

import com.example.storeservice.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    boolean existsByStoreIdAndName(Long storeId, String name);
}


