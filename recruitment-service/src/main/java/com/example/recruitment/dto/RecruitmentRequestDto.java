package com.example.recruitment.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RecruitmentRequestDto {
    private Long userId;
    private Long storeId;
    private String title;
    private String description;
    private LocalDateTime deadlineTime;
}
