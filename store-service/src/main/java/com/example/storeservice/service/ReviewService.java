package com.example.storeservice.service;

import com.example.storeservice.client.ReviewClient;
import com.example.storeservice.dto.ReviewResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewClient reviewClient;

    public List<ReviewResponseDto> getReviewsByStoreId(Long storeId) {
        return reviewClient.getReviewsByStoreId(storeId);
    }
}
