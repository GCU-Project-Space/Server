package com.example.storeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreResponseDto {
    private Long id;
    private String name;
    private String phone;
    private String location;
    private String description;
    private String openHours;
    private Integer minOrderPrice;
} 