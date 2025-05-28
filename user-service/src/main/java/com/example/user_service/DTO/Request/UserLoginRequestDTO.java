package com.example.user_service.DTO.Request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginRequestDTO{
    private String email;
    private String password;
}