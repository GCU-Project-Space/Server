package com.example.storeservice.controller;

import com.example.storeservice.common.BaseResponse;
import com.example.storeservice.dto.MenuRequestDto;
import com.example.storeservice.service.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.storeservice.dto.MenuPartialUpdateDto;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stores/{storeId}/menus")
public class MenuController {

    private final MenuService menuService;

    @Operation(summary = "메뉴 등록", description = "가게에 메뉴를 등록합니다.")
    @PostMapping
    public ResponseEntity<BaseResponse<Void>> createMenu(
            @PathVariable Long storeId,
            @RequestBody @Valid MenuRequestDto dto) {
        menuService.createMenu(storeId, dto); // ← storeId 추가!
        return ResponseEntity.ok(BaseResponse.success(1010, "메뉴 등록 성공", null));
    }

    @Operation(summary = "메뉴 일부 수정", description = "특정 가게의 메뉴 일부 정보를 수정합니다.")
    @PatchMapping("/{menuId}")
    public ResponseEntity<BaseResponse<Void>> updateMenuPartially(
            @PathVariable Long storeId,
            @PathVariable Long menuId,
            @RequestBody MenuPartialUpdateDto updateDto) {
        menuService.updateMenuPartially(menuId, updateDto);
        return ResponseEntity.ok(BaseResponse.success(1011, "메뉴 수정 완료", null));
    }

}
