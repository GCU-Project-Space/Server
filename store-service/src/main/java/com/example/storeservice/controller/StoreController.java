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


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stores")
public class StoreController {

    private final StoreService storeService;

    @Operation(summary = "가게 등록", description = "새로운 가게를 등록합니다.")
    @PostMapping
    public ResponseEntity<?> createStore(@Valid @RequestBody StoreRequestDto requestDto) {
        storeService.createStore(requestDto);
        // 성공적으로 가게가 등록되었을 때의 응답
        Map<String, Object> response = new HashMap<>();
        response.put("isSuccess", true);
        response.put("code", 1001); // CREATED
        response.put("message", "가게 등록 성공");
        response.put("data", null); // 또는 등록된 store 정보 등

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

}
