package com.example.storeservice.client;

import com.example.storeservice.dto.ReviewResponseDto;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Component
@Primary
public class FakeReviewClient implements ReviewClient {

    @Override
    public List<ReviewResponseDto> getReviewsByStoreId(Long storeId) {
        ReviewResponseDto review1 = new ReviewResponseDto();
        review1.setId(1L);
        review1.setStoreId(storeId);
        review1.setUserId(101L);
        review1.setUserNickname("김갑수");
        review1.setContent("정말 맛있어요!");
        review1.setRating(5);
        review1.setCreatedAt("2025-05-01T12:00:00");

        ReviewResponseDto review2 = new ReviewResponseDto();
        review2.setId(2L);
        review2.setStoreId(storeId);
        review2.setUserId(102L);
        review2.setUserNickname("박용례");
        review2.setContent("양도 많고 친절했습니다.");
        review2.setRating(4);
        review2.setCreatedAt("2025-05-02T10:30:00");

        return List.of(review1, review2);
    }
}
