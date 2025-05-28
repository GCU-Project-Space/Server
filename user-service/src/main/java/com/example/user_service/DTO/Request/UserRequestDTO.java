package com.example.user_service.DTO.Request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor

public class UserRequestDTO{
    private String nickname;
    private String password;
    private String school;
    private String phoneNumber;
    private String email;
    private int schoolId;
    private String userType;
}