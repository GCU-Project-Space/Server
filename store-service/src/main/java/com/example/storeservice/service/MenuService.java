package com.example.storeservice.service;

import com.example.storeservice.dto.MenuRequestDto;
import com.example.storeservice.entity.Menu;
import com.example.storeservice.entity.Store;
import com.example.storeservice.exception.CustomException;
import com.example.storeservice.exception.ErrorCode;
import com.example.storeservice.mapper.MenuMapper;
import com.example.storeservice.repository.MenuRepository;
import com.example.storeservice.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final StoreRepository storeRepository;
    private final MenuMapper menuMapper;

    @Transactional
    public void createMenu(MenuRequestDto dto) {
        Store store = storeRepository.findById(dto.getStoreId())
            .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND, "해당 가게를 찾을 수 없습니다."));

        if (menuRepository.existsByStoreIdAndName(dto.getStoreId(), dto.getName())) {
            throw new CustomException(ErrorCode.DUPLICATE_MENU, "이미 등록된 메뉴 이름입니다.");
        }
        Menu menu = menuMapper.toEntity(dto);
        menu.setStore(store);
        menuRepository.save(menu);
    }

}
