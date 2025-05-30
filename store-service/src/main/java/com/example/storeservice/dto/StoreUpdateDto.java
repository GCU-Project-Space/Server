package com.example.storeservice.dto;

import com.example.storeservice.entity.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class StoreUpdateDto {

    @Schema(description = "가게 이름", example = "소머리국밥")
    private String name;

    @Schema(description = "전화번호", example = "010-1234-5678")
    private String phone;

    @Schema(description = "가게 위치", example = "서울시 강남구")
    private String location;

    @Schema(description = "가게 설명", example = "20년 전통의 맛집")
    private String description;

    @Schema(description = "영업시간", example = "10:00 ~ 22:00")
    private String openHours;

    @Schema(description = "최소 주문 금액", example = "15000")
    private Integer minOrderPrice;

    @Schema(description = "가게 카테고리", example = "KOREAN")
    private Category category;
}
