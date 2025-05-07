package com.example.storeservice.service;

import com.example.storeservice.dto.StoreRequestDto;
import com.example.storeservice.entity.Store;
import com.example.storeservice.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    public Store createStore(StoreRequestDto requestDto) {
        // 가게 이름 중복 확인
        if (storeRepository.existsByName(requestDto.getName())) {
            throw new IllegalArgumentException("이미 등록된 가게 이름입니다.");
        }

        Store store = new Store(
                requestDto.getName(),
                requestDto.getPhone(),
                requestDto.getLocation(),
                requestDto.getDescription(),
                requestDto.getOpenHours(),
                requestDto.getMinOrderPrice()
        );

        return storeRepository.save(store);
    }
}
