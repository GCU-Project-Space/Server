package com.example.storeservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class MenuResponseDto {
    private Long id;
    private String name;
    private String description;
    private int price;

    private Integer discountRate;      // 할인율 (예: 20)
    private Integer discountedPrice;   // 할인 적용된 가격 (예: 8000)

    private String imageUrl;
    private Map<String, List<String>> options;
}
