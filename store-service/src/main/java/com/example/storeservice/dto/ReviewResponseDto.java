package com.example.storeservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewResponseDto {
    private Long id;
    private Long userId;
    private String userNickname;
    private String content;
    private int rating;
    private String createdAt;
    private Long storeId;
}
