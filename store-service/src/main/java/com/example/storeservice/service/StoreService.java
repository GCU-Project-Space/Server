package com.example.storeservice.service;

import com.example.storeservice.dto.StoreRequestDto;
import com.example.storeservice.entity.Store;
import com.example.storeservice.repository.StoreRepository;
import com.example.storeservice.mapper.StoreMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.storeservice.dto.StoreUpdateDto;
import org.springframework.transaction.annotation.Transactional;
import com.example.storeservice.exception.CustomException;
import com.example.storeservice.exception.ErrorCode;
import com.example.storeservice.dto.StoreResponseDto;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final StoreMapper storeMapper;


    @Transactional
    public void createStore(StoreRequestDto requestDto) {
    if (storeRepository.existsByName(requestDto.getName())) {
        throw new CustomException(ErrorCode.DUPLICATE_NAME);
        }

    if (storeRepository.existsByPhone(requestDto.getPhone())) {
        throw new CustomException(ErrorCode.DUPLICATE_PHONE);
        }

    if (storeRepository.existsByLocation(requestDto.getLocation())) {
        throw new CustomException(ErrorCode.DUPLICATE_LOCATION);
        }

    Store store = storeMapper.toEntity(requestDto);
    storeRepository.save(store);
        
    }

    @Transactional
    public void updateStore(Long storeId, StoreUpdateDto updateDto) {
        Store store = storeRepository.findById(storeId)
            .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));

        // 이름 중복 검사
        if (updateDto.getName() != null && !updateDto.getName().isBlank()) {
            if (storeRepository.existsByNameAndIdNot(updateDto.getName(), storeId)) {
                throw new CustomException(ErrorCode.DUPLICATE_NAME);
            }
            store.setName(updateDto.getName());
        }

        // 전화번호 중복 검사
        if (updateDto.getPhone() != null && !updateDto.getPhone().isBlank()) {
            if (storeRepository.existsByPhoneAndIdNot(updateDto.getPhone(), storeId)) {
                throw new CustomException(ErrorCode.DUPLICATE_PHONE);
            }
            store.setPhone(updateDto.getPhone());
        }

        // 위치 중복 검사
        if (updateDto.getLocation() != null && !updateDto.getLocation().isBlank()) {
            if (storeRepository.existsByLocationAndIdNot(updateDto.getLocation(), storeId)) {
                throw new CustomException(ErrorCode.DUPLICATE_LOCATION);
            }
            store.setLocation(updateDto.getLocation());
        }

        // 나머지 필드
        if (updateDto.getDescription() != null && !updateDto.getDescription().isBlank()) {
            store.setDescription(updateDto.getDescription());
        }
        if (updateDto.getOpenHours() != null && !updateDto.getOpenHours().isBlank()) {
            store.setOpenHours(updateDto.getOpenHours());
        }
        if (updateDto.getMinOrderPrice() != null && updateDto.getMinOrderPrice() > 0) {
            store.setMinOrderPrice(updateDto.getMinOrderPrice());
        }
    }

    // 가게 삭제
    @Transactional
    public void deleteStore(Long storeId) {
        Store store = storeRepository.findById(storeId)
        .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));
        storeRepository.delete(store);
    }

    // 가게 조회
    @Transactional(readOnly = true)
    public StoreResponseDto getStoreById(Long storeId) {
        Store store = storeRepository.findById(storeId)
            .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));
        return storeMapper.toResponseDto(store);
    }

    // 전체 가게 조회
    @Transactional(readOnly = true)
    public List<StoreResponseDto> getAllStores() {
        List<Store> stores = storeRepository.findAll();
        return stores.stream()
            .map(storeMapper::toResponseDto)
            .collect(Collectors.toList());
    }
}
