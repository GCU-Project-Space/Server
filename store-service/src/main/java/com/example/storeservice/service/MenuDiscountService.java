package com.example.storeservice.service;

import com.example.storeservice.dto.MenuDiscountRequestDto;
import com.example.storeservice.entity.Menu;
import com.example.storeservice.entity.MenuDiscount;
import com.example.storeservice.exception.CustomException;
import com.example.storeservice.exception.ErrorCode;
import com.example.storeservice.repository.MenuDiscountRepository;
import com.example.storeservice.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MenuDiscountService {

    private final MenuRepository menuRepository;
    private final MenuDiscountRepository menuDiscountRepository;

    /**
     * 메뉴 할인 등록
     */
    @Transactional
    public void createDiscount(Long storeId, Long menuId, MenuDiscountRequestDto dto) {
        Menu menu = menuRepository.findById(menuId)
            .orElseThrow(() -> new CustomException(ErrorCode.MENU_NOT_FOUND, "해당 메뉴를 찾을 수 없습니다."));

        // 메뉴가 storeId에 속해 있는지 검증
        if (!menu.getStore().getId().equals(storeId)) {
            throw new CustomException(ErrorCode.INVALID_MENU_ID, "해당 메뉴는 요청한 가게의 메뉴가 아닙니다.");
        }

        if (menuDiscountRepository.existsByMenuId(menuId)) {
            throw new CustomException(ErrorCode.DUPLICATE_DISCOUNT, "이미 해당 메뉴에 할인 정보가 존재합니다.");
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
        Menu menu = menuRepository.findById(menuId)
            .orElseThrow(() -> new CustomException(ErrorCode.MENU_NOT_FOUND, "해당 메뉴를 찾을 수 없습니다."));

        // 메뉴가 storeId에 속해 있는지 검증
        if (!menu.getStore().getId().equals(storeId)) {
            throw new CustomException(ErrorCode.INVALID_MENU_ID, "해당 메뉴는 요청한 가게의 메뉴가 아닙니다.");
        }

        if (!menuDiscountRepository.existsByMenuId(menuId)) {
            throw new CustomException(ErrorCode.DISCOUNT_NOT_FOUND, "해당 메뉴의 할인 정보가 존재하지 않습니다.");
        }

        menuDiscountRepository.deleteByMenuId(menuId);
    }
}
