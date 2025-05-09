package com.example.user_service.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginRequestDTO{
    private String nickname;
    private String email;
    private String password;
}