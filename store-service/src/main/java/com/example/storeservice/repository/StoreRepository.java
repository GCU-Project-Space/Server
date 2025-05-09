package com.example.storeservice.repository;

import com.example.storeservice.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
    boolean existsByName(String name);

    boolean existsByPhone(String phone);

    boolean existsByLocation(String location);
}
