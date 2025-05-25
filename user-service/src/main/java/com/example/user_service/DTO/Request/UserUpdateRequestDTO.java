package com.example.user_service.DTO.Request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserUpdateRequestDTO{
    private String nickname;
    private String phoneNumber;
    private String password;
}