package com.example.recruitment.dto.order;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class OrderRequestDto {
    private Long groupId;
    private Long userId;
    private Long storeId;
    private List<MenuDto> menus;

    @Getter @Setter
    public static class MenuDto {
        private Long menuId;
        private String menuName;
        private int basePrice;
        private int count;
        private List<OptionDto> options;
    }

    @Getter @Setter
    public static class OptionDto {
        private Long optionId;
        private String optionName;
        private int price;
    }
}
