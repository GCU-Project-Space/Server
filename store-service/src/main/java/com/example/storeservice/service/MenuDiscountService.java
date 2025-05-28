package com.example.storeservice.service;

import com.example.storeservice.dto.MenuDiscountRequestDto;
import com.example.storeservice.entity.Menu;
import com.example.storeservice.entity.MenuDiscount;
import com.example.storeservice.exception.CustomException;
import com.example.storeservice.exception.ErrorCode;
import com.example.storeservice.repository.MenuDiscountRepository;
import com.example.storeservice.repository.MenuRepository;
import com.example.storeservice.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.storeservice.entity.Store;



@Service
@RequiredArgsConstructor
public class MenuDiscountService {

    private final MenuRepository menuRepository;
    private final MenuDiscountRepository menuDiscountRepository;
    private final StoreRepository storeRepository;

    /**
     * 메뉴 할인 등록
     */
    @Transactional
    public void createDiscount(Long storeId, Long menuId, MenuDiscountRequestDto dto) {

        Store store = storeRepository.findById(storeId)
            .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));
        
        Menu menu = menuRepository.findById(menuId)
            .orElseThrow(() -> new CustomException(ErrorCode.MENU_NOT_FOUND));

        // 메뉴가 storeId에 속해 있는지 검증
        if (!menu.getStore().getId().equals(storeId)) {
            throw new CustomException(ErrorCode.INVALID_MENU_ID);
        }

        if (menuDiscountRepository.existsByMenuId(menuId)) {
            throw new CustomException(ErrorCode.DUPLICATE_DISCOUNT);
        }

        MenuDiscount discount = new MenuDiscount();
        discount.setMenu(menu);
        discount.setDiscountRate(dto.getDiscountRate());

        menuDiscountRepository.save(discount);
    }

    /**
     * 메뉴 할인 삭제
     */
    @Transactional
    public void deleteDiscount(Long storeId, Long menuId) {

        Store store = storeRepository.findById(storeId)
            .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));
        
        Menu menu = menuRepository.findById(menuId)
            .orElseThrow(() -> new CustomException(ErrorCode.MENU_NOT_FOUND));

        // 메뉴가 storeId에 속해 있는지 검증
        if (!menu.getStore().getId().equals(storeId)) {
            throw new CustomException(ErrorCode.INVALID_MENU_ID);
        }

        if (!menuDiscountRepository.existsByMenuId(menuId)) {
            throw new CustomException(ErrorCode.DISCOUNT_NOT_FOUND);
        }

        menuDiscountRepository.deleteByMenuId(menuId);
    }
}
