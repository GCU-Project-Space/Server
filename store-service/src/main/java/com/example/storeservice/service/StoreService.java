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


@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final StoreMapper storeMapper;


    @Transactional
    public void createStore(StoreRequestDto requestDto) {
    if (storeRepository.existsByName(requestDto.getName())) {
        throw new CustomException(ErrorCode.DUPLICATE_PHONE, "이미 존재하는 가게 이름입니다.");
        }

    if (storeRepository.existsByPhone(requestDto.getPhone())) {
        throw new CustomException(ErrorCode.DUPLICATE_PHONE, "이미 존재하는 전화번호입니다.");
        }

    if (storeRepository.existsByLocation(requestDto.getLocation())) {
        throw new CustomException(ErrorCode.DUPLICATE_LOCATION, "이미 존재하는 위치입니다.");
        }

    Store store = storeMapper.toEntity(requestDto);
    storeRepository.save(store);
        
    }

    @Transactional
    public void updateStore(Long storeId, StoreUpdateDto updateDto) {
        Store store = storeRepository.findById(storeId)
            .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND,"가게를 찾을 수 없습니다."));

        if (updateDto.getName() != null && !updateDto.getName().isBlank()) {
            store.setName(updateDto.getName());
        }
        if (updateDto.getPhone() != null && !updateDto.getPhone().isBlank()) {
            store.setPhone(updateDto.getPhone());
        }
        if (updateDto.getLocation() != null && !updateDto.getLocation().isBlank()) {
            store.setLocation(updateDto.getLocation());
        }
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

    @Transactional
    public void deleteStore(Long storeId) {
        Store store = storeRepository.findById(storeId)
        .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND,"가게를 찾을 수 없습니다."));
        storeRepository.delete(store);
}


}
