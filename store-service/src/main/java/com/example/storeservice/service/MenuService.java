package com.example.storeservice.service;

import com.example.storeservice.dto.MenuRequestDto;
import com.example.storeservice.dto.MenuPartialUpdateDto;
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
import java.util.List;
import java.util.stream.Collectors;
import com.example.storeservice.dto.MenuResponseDto;
import com.example.storeservice.repository.MenuDiscountRepository;


@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final StoreRepository storeRepository;
    private final MenuMapper menuMapper;
    private final MenuDiscountRepository menuDiscountRepository;

    /**
     * 메뉴 등록
     */
    @Transactional
    public void createMenu(Long storeId, MenuRequestDto dto) {
        // 1. 가게 존재 확인
        Store store = storeRepository.findById(storeId)
            .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND, "해당 가게를 찾을 수 없습니다."));

        // 2. 중복 메뉴 이름 검사
        if (menuRepository.existsByStoreIdAndName(storeId, dto.getName())) {
            throw new CustomException(ErrorCode.DUPLICATE_MENU, "이미 등록된 메뉴 이름입니다.");
        }

        // 3. DTO → Entity 변환
        Menu menu = menuMapper.toEntity(dto);
        menu.setStore(store);

        // 4. 먼저 Menu 저장해서 menu.id 생성
        menu = menuRepository.save(menu);

    }


    /**
     * 메뉴 부분 수정
     */
    @Transactional
    public void updateMenuPartially(Long menuId, MenuPartialUpdateDto dto) {
        Menu menu = menuRepository.findById(menuId)
            .orElseThrow(() -> new CustomException(ErrorCode.MENU_NOT_FOUND, "해당 메뉴를 찾을 수 없습니다."));

        // 이름 중복 검사
        if (dto.getName() != null && !dto.getName().isBlank()) {
            if (!menu.getName().equals(dto.getName()) &&
                menuRepository.existsByStoreIdAndName(menu.getStore().getId(), dto.getName())) {
                throw new CustomException(ErrorCode.DUPLICATE_MENU, "이미 존재하는 메뉴 이름입니다.");
            }
            menu.setName(dto.getName());
        }

        if (dto.getPrice() != null) {
            menu.setPrice(dto.getPrice());
        }

        if (dto.getDescription() != null) {
            menu.setDescription(dto.getDescription());
        }

        if (dto.getOptions() != null) {
            menu.setOptions(dto.getOptions());
        }

        if (dto.getImageUrl() != null && !dto.getImageUrl().isBlank()) {
            menu.setImageUrl(dto.getImageUrl());
        }

        menuRepository.save(menu); // 선택사항 (변경 감지 덕분에 없어도 됨)
    }

    /**
     * 메뉴 삭제
     */
    @Transactional
    public void deleteMenu(Long menuId) {
        Menu menu = menuRepository.findById(menuId)
            .orElseThrow(() -> new CustomException(ErrorCode.MENU_NOT_FOUND, "해당 메뉴를 찾을 수 없습니다."));
        menuRepository.delete(menu);
    }

    /**
     * 메뉴 전체 삭제
     */
    @Transactional
    public void deleteAllMenusByStoreId(Long storeId) {
        Store store = storeRepository.findById(storeId)
            .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND, "해당 가게를 찾을 수 없습니다."));

        List<Menu> menus = menuRepository.findByStoreId(storeId);
        menuRepository.deleteAll(menus);
    }


    /**
     * 메뉴 전체 불러오기
     */
    @Transactional(readOnly = true)
    public List<MenuResponseDto> getMenusByStoreId(Long storeId) {
        
        Store store = storeRepository.findById(storeId)
            .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND, "해당 가게를 찾을 수 없습니다."));

        List<Menu> menus = menuRepository.findByStoreId(storeId);

        return menus.stream()
            .map(menu -> {
                MenuResponseDto dto = menuMapper.toDto(menu);

                menuDiscountRepository.findByMenuId(menu.getId()).ifPresent(discount -> {
                    int rate = discount.getDiscountRate();
                    dto.setDiscountRate(rate);
                    dto.setDiscountedPrice(menu.getPrice() * (100 - rate) / 100);
                });

                return dto;
            })
            .collect(Collectors.toList());
    }

}
