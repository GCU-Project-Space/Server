package com.example.storeservice.service;

import com.example.storeservice.dto.StoreRequestDto;
import com.example.storeservice.entity.Store;
import com.example.storeservice.repository.StoreRepository;
import com.example.storeservice.mapper.StoreMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final StoreMapper storeMapper;

    public void createStore(StoreRequestDto requestDto) {

        if (storeRepository.existsByName(requestDto.getName())) {
            throw new IllegalArgumentException("이미 존재하는 가게 이름입니다.");
        }
        if (storeRepository.existsByPhone(requestDto.getPhone())) {
            throw new IllegalArgumentException("이미 존재하는 전화번호입니다.");
        }
        if (storeRepository.existsByLocation(requestDto.getLocation())) {
            throw new IllegalArgumentException("이미 존재하는 위치입니다.");
        }
        Store store = storeMapper.toEntity(requestDto);
        storeRepository.save(store);
    }
}
