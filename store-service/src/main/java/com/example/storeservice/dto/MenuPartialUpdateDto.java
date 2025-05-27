package com.example.storeservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class MenuPartialUpdateDto {

    @Schema(description = "메뉴 이름", example = "신짜장")
    private String name;

    @Schema(description = "메뉴 가격", example = "8500")
    @Min(value = 0, message = "가격은 0원 이상이어야 합니다.")
    private Integer price;

    @Schema(description = "메뉴 설명", example = "매콤한 신메뉴 짜장면")
    private String description;

    @Schema(description = "메뉴 옵션", example = "{\"맛\": [\"순한맛\", \"매운맛\"]}")
    private Map<String, List<String>> options;

    @Schema(description = "이미지 URL", example = "https://cdn.example.com/menu.jpg")
    private String imageUrl;
}
