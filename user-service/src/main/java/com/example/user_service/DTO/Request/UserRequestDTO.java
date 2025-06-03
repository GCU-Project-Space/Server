package com.example.user_service.DTO.Request;

import lombok.*;

import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@NoArgsConstructor

public class UserRequestDTO {
    @Schema(description = "사용자 닉네임")
    private String nickname;

    @Schema(description = "비밀번호")
    private String password;

    @Schema(description = "학교 이름")
    private String school;

    @Schema(description = "전화번호")
    private String phoneNumber;

    @Schema(description = "이메일")
    private String email;

    @Schema(description = "학교 ID")
    private int schoolId;

    @Schema(description = "유저 타입 (USER 또는 OWNER)")
    private String userType;

    @Schema(description = "가게 ID (OWNER일 경우 사용)")
    private Long storeId;
}