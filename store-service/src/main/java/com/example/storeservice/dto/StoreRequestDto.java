package com.example.storeservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StoreRequestDto {

    @Schema(description = "가게 이름", example = "소머리국밥", required = true)
    @NotBlank(message = "가게 이름은 필수입니다.")
    private String name;

    @Schema(description = "전화번호", example = "010-1234-5611", required = true)
    @NotBlank(message = "전화번호는 필수입니다.")
    private String phone;

    @Schema(description = "가게 위치", example = "서울시 용산구", required = true)
    @NotBlank(message = "위치는 필수입니다.")
    private String location;

    @Schema(description = "가게 설명", example = "정말 맛있는 국밥!")
    private String description;

    @Schema(description = "영업 시간", example = "10:00~21:00", required = true)
    @NotBlank(message = "영업시간은 필수입니다.")
    private String openHours;

    @Schema(description = "최소 주문 금액", example = "10000", required = true)
    @Min(value = 0, message = "최소 주문 금액은 0 이상이어야 합니다.")
    private int minOrderPrice;
}
