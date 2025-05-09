package com.example.storeservice.repository;

import com.example.storeservice.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;


public interface MenuRepository extends JpaRepository<Menu, Long> {
    boolean existsByStoreIdAndName(Long storeId, String name);
    List<Menu> findByStoreId(Long storeId);

}


