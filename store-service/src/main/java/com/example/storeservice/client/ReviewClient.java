package com.example.storeservice.client;

import com.example.storeservice.dto.ReviewResponseDto;
import java.util.List;

public interface ReviewClient {
    List<ReviewResponseDto> getReviewsByStoreId(Long storeId);
}
