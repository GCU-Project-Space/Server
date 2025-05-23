package com.example.storeservice.controller;

import com.example.storeservice.dto.StoreRequestDto;
import com.example.storeservice.dto.StoreResponseDto;
import com.example.storeservice.entity.Store;
import com.example.storeservice.service.StoreService;
import com.example.storeservice.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import java.util.HashMap;
import java.util.Map;
import com.example.storeservice.dto.StoreUpdateDto;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stores")
public class StoreController {

    private final StoreService storeService;

    @Operation(summary = "가게 등록", description = "새로운 가게를 등록합니다.")
    @PostMapping
    public ResponseEntity<BaseResponse<Void>> createStore(@Valid @RequestBody StoreRequestDto requestDto) {
        storeService.createStore(requestDto);
        return ResponseEntity.ok(BaseResponse.success(null));
    }

    @GetMapping("/{storeId}")
    @Operation(summary = "단일 가게 조회", description = "storeId에 해당하는 가게의 상세 정보를 조회합니다.")
    public ResponseEntity<BaseResponse<StoreResponseDto>> getStoreById(@PathVariable Long storeId) {
        StoreResponseDto store = storeService.getStoreById(storeId);
        return ResponseEntity.ok(BaseResponse.success(store));
    }

    @GetMapping
    @Operation(summary = "전체 가게 목록 조회", description = "등록된 모든 가게 정보를 조회합니다.")
    public ResponseEntity<BaseResponse<List<StoreResponseDto>>> getAllStores() {
        List<StoreResponseDto> stores = storeService.getAllStores();
        return ResponseEntity.ok(BaseResponse.success(stores));
    }

    @PatchMapping("/{storeId}")
    @Operation(summary = "가게 정보 수정", description = "가게의 일부 정보를 수정합니다.")
    public ResponseEntity<BaseResponse<Void>> updateStore(
        @PathVariable Long storeId,
        @RequestBody StoreUpdateDto updateDto) {

        storeService.updateStore(storeId, updateDto);
        return ResponseEntity.ok(BaseResponse.success(null));
    }

    @Operation(summary = "가게 삭제", description = "storeId에 해당하는 가게 정보를 삭제합니다.")
    @DeleteMapping("/{storeId}")
    public ResponseEntity<BaseResponse<Void>> deleteStore(@PathVariable Long storeId) {
        storeService.deleteStore(storeId);
        return ResponseEntity.ok(BaseResponse.success(null));
    }

}
