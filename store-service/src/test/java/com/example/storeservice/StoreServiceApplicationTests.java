package com.example.storeservice;

import com.example.storeservice.client.ReviewClient;
import com.example.storeservice.dto.ReviewResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

@SpringBootTest
public class StoreServiceApplicationTests {

    @MockBean
    private ReviewClient reviewClient;

    @Test
    void contextLoads() {
        // 목데이터 설정
        ReviewResponseDto review1 = new ReviewResponseDto();
        review1.setId(1L);
        review1.setStoreId(1L);
        review1.setUserId(101L);
        review1.setUserNickname("김갑수");
        review1.setContent("정말 맛있어요!");
        review1.setRating(5);
        review1.setCreatedAt("2025-05-01T12:00:00");

        ReviewResponseDto review2 = new ReviewResponseDto();
        review2.setId(2L);
        review2.setStoreId(1L);
        review2.setUserId(102L);
        review2.setUserNickname("박용례");
        review2.setContent("양도 많고 친절했습니다.");
        review2.setRating(4);
        review2.setCreatedAt("2025-05-02T10:30:00");

        // 목 동작 정의
        given(reviewClient.getReviewsByStoreId(anyLong()))
            .willReturn(List.of(review1, review2));

        List<ReviewResponseDto> reviews = reviewClient.getReviewsByStoreId(1L);
        assertEquals(2, reviews.size());
    }
}

