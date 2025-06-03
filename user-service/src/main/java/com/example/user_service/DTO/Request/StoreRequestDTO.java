package com.example.user_service.DTO.Request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor

public class StoreRequestDTO {
    private String name;
    private String phone;
    private String location;
    private String openHours;
    private int minOrderPrice;
    private String imageUrl;
    private String discription;
}

