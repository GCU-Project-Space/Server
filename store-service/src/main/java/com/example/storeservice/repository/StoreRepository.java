package com.example.storeservice.repository;

import com.example.storeservice.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
    // 가게 등록 시 중복 검사
    boolean existsByName(String name);
    boolean existsByPhone(String phone);
    boolean existsByLocation(String location);

    // 가게 수정 시 중복 검사
    boolean existsByNameAndIdNot(String name, Long id);
    boolean existsByPhoneAndIdNot(String phone, Long id);
    boolean existsByLocationAndIdNot(String location, Long id);

}
