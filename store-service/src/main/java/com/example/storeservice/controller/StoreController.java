package com.example.storeservice.controller;

import com.example.storeservice.dto.StoreRequestDto;
import com.example.storeservice.entity.Store;
import com.example.storeservice.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import java.util.HashMap;
import java.util.Map;
import com.example.storeservice.dto.StoreUpdateDto;
import com.example.storeservice.common.BaseResponse;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stores")
public class StoreController {

    private final StoreService storeService;

    @Operation(summary = "가게 등록", description = "새로운 가게를 등록합니다.")
    @PostMapping
    public ResponseEntity<BaseResponse<Void>> createStore(@Valid @RequestBody StoreRequestDto requestDto) {
        storeService.createStore(requestDto);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(BaseResponse.success(1001, "가게 등록 성공", null));
    }


    @PatchMapping("/{storeId}")
    @Operation(summary = "가게 정보 수정", description = "가게의 일부 정보를 수정합니다.")
    public ResponseEntity<BaseResponse<Void>> updateStore(
        @PathVariable Long storeId,
        @RequestBody StoreUpdateDto updateDto) {

        storeService.updateStore(storeId, updateDto);
        return ResponseEntity.ok(BaseResponse.success(1002, "가게 정보 수정 완료", null));
    }

    @Operation(summary = "가게 삭제", description = "storeId에 해당하는 가게 정보를 삭제합니다.")
    @DeleteMapping("/{storeId}")
    public ResponseEntity<BaseResponse<Void>> deleteStore(@PathVariable Long storeId) {
        storeService.deleteStore(storeId);
        return ResponseEntity.ok(BaseResponse.success(1003, "가게 삭제 성공", null));
    }

}
