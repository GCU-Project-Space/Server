package com.example.recruitment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecruitmentRequestDto {

    @NotNull(message = "userId는 필수입니다.")
    private Long userId;

    @NotNull(message = "storeId는 필수입니다.")
    private Long storeId;

    @NotBlank(message = "제목은 비어 있을 수 없습니다.")
    private String title;

    @Size(min = 10, message = "설명은 최소 10자 이상이어야 합니다.")
    private String description;

    @NotNull(message = "마감 시간은 필수입니다.")
    private LocalDateTime deadlineTime;
}



//원래 dto 코드
/*
 * package com.example.recruitment.dto;

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
 */
