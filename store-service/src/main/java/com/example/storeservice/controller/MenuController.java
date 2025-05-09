package com.example.storeservice.controller;

import com.example.storeservice.common.BaseResponse;
import com.example.storeservice.dto.MenuRequestDto;
import com.example.storeservice.service.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menus")
public class MenuController {

    private final MenuService menuService;

    @Operation(summary = "메뉴 등록", description = "가게에 메뉴를 등록합니다.")
    @PostMapping
    public ResponseEntity<BaseResponse<Void>> createMenu(@Valid @RequestBody MenuRequestDto dto) {
        menuService.createMenu(dto);
        return ResponseEntity.ok(BaseResponse.success(1004, "메뉴 등록 성공", null));
    }
}
