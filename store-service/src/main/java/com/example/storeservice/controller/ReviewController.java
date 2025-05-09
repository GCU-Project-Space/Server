package com.example.storeservice.controller;

import com.example.storeservice.common.BaseResponse;
import com.example.storeservice.dto.ReviewResponseDto;
import com.example.storeservice.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stores/{storeId}/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "가게 리뷰 목록 조회", description = "특정 가게에 속한 리뷰 목록을 조회합니다.")
    @GetMapping
    public ResponseEntity<BaseResponse<List<ReviewResponseDto>>> getReviewsByStoreId(
            @PathVariable Long storeId) {

        List<ReviewResponseDto> reviews = reviewService.getReviewsByStoreId(storeId);
        return ResponseEntity.ok(BaseResponse.success(1000, "가게 리뷰 목록 조회 성공", reviews));
    }
}
