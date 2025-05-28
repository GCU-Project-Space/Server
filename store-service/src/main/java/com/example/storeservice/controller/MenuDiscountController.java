package com.example.storeservice.controller;

import com.example.storeservice.response.BaseResponse;
import com.example.storeservice.dto.MenuDiscountRequestDto;
import com.example.storeservice.service.MenuDiscountService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stores/{storeId}/menus/{menuId}/discount")
public class MenuDiscountController {

    private final MenuDiscountService menuDiscountService;

    @Operation(summary = "메뉴 할인 등록", description = "특정 메뉴에 할인율을 등록합니다.")
    @PostMapping
    public ResponseEntity<BaseResponse<Void>> createDiscount(
            @PathVariable Long storeId,
            @PathVariable Long menuId,
            @RequestBody @Valid MenuDiscountRequestDto dto) {

        menuDiscountService.createDiscount(storeId, menuId, dto);
        return ResponseEntity.ok(BaseResponse.success(null));
    }

    @Operation(summary = "메뉴 할인 삭제", description = "특정 메뉴의 할인율 정보를 삭제합니다.")
    @DeleteMapping
    public ResponseEntity<BaseResponse<Void>> deleteDiscount(
            @PathVariable Long storeId,
            @PathVariable Long menuId) {

        menuDiscountService.deleteDiscount(storeId, menuId);
        return ResponseEntity.ok(BaseResponse.success(null));
    }
}
