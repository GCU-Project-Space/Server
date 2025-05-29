package com.example.storeservice.controller;

import com.example.storeservice.response.BaseResponse;
import com.example.storeservice.dto.MenuRequestDto;
import com.example.storeservice.service.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.storeservice.dto.MenuPartialUpdateDto;
import java.util.List;
import com.example.storeservice.dto.MenuResponseDto;

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
        return ResponseEntity.ok(BaseResponse.success(null));
    }

    @Operation(summary = "메뉴 일부 수정", description = "특정 가게의 메뉴 일부 정보를 수정합니다.")
    @PatchMapping("/{menuId}")
    public ResponseEntity<BaseResponse<Void>> updateMenuPartially(
            @PathVariable Long storeId,
            @PathVariable Long menuId,
            @RequestBody @Valid MenuPartialUpdateDto updateDto) {
        menuService.updateMenuPartially(menuId, updateDto);
        return ResponseEntity.ok(BaseResponse.success(null));
    }

    @Operation(summary = "메뉴 삭제", description = "특정 메뉴를 삭제합니다.")
    @DeleteMapping("/{menuId}")
    public ResponseEntity<BaseResponse<Void>> deleteMenu(
            @PathVariable Long storeId,
            @PathVariable Long menuId) {
        menuService.deleteMenu(menuId);
        return ResponseEntity.ok(BaseResponse.success(null));
    }

    @Operation(summary = "가게 메뉴 목록 조회", description = "가게의 모든 메뉴를 조회하며, 할인 시 할인가 포함")
    @GetMapping
    public ResponseEntity<BaseResponse<List<MenuResponseDto>>> getMenusByStoreId(
            @PathVariable Long storeId) {
        List<MenuResponseDto> result = menuService.getMenusByStoreId(storeId);
        return ResponseEntity.ok(BaseResponse.success(result));
    }
}
