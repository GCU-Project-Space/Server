package com.example.user_service.DTO;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserResponseDTO {
    private Long id;
    private String nickname;
    private String school;
    private String phoneNumber;
    private String email;
    private int schoolId;
    private LocalDateTime createdAt;
}