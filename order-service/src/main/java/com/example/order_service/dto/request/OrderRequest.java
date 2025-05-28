package com.example.order_service.dto.request;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.order_service.entity.MenuOptionSnapshot;
import com.example.order_service.entity.MenuSnapshot;
import com.example.order_service.entity.OrderEntity;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderRequest {
    @Schema(example = "1")
    private Long groupId;

    @Schema(example = "1")
    private Long userId;

    @Schema(example = "1")
    private Long storeId;

    @Builder.Default
    private List<Menu> menus = new ArrayList<>();

    public OrderEntity toEntity() {
        OrderEntity order = OrderEntity.builder()
        .groupId(getGroupId())
        .userId(getUserId())
        .storeId(getStoreId())
        .createdAt(LocalDateTime.now())
        .menus(getMenus().stream()
            .map(item -> item.toEntity())
            .collect(Collectors.toList())
        )
        .build();
        
        // 양방 관계 매핑
        for (MenuSnapshot menu : order.getMenus()) {
            menu.setOrder(order);
        }

        return order;
    }

    public static OrderRequest fromEntity(OrderEntity entity) {
        return OrderRequest.builder()
        .groupId(entity.getGroupId())
        .userId(entity.getUserId())
        .storeId(entity.getStoreId())
        .menus(entity.getMenus().stream()
            .<Menu>map(menu -> Menu.builder()
                .menuId(menu.getMenuId())
                .menuName(menu.getMenuName())
                .basePrice(menu.getBasePrice())
                .count(menu.getCount())
                .options(menu.getOptions().stream()
                    .<MenuOption>map(option -> MenuOption.builder()
                        .optionId(option.getOptinoId())
                        .optionName(option.getOptionName())
                        .price(option.getPrice())
                        .build())
                    .collect(Collectors.toList()))
                .build())
            .collect(Collectors.toList()))
        .build();
    }

}

@Hidden
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
class Menu {
    
    @Schema(example = "1")
    private Long menuId;

    @Schema(example = "맛있는 치킨")
    private String menuName;

    @Schema(example = "11111")
    private int basePrice;

    @Schema(example = "1")
    private int count;

    private List<MenuOption> options;

    public MenuSnapshot toEntity() {
        MenuSnapshot menu = MenuSnapshot.builder()
        .menuId(getMenuId())
        .menuName(getMenuName())
        .basePrice(getBasePrice())
        .count(getCount())
        .options(getOptions().stream()
            .map(item -> item.toEntity())
            .collect(Collectors.toList())
        )
        .build();
        
        // 양방 관계 설정
        for (MenuOptionSnapshot option : menu.getOptions()) {
            option.setMenu(menu);
        }

        return menu;
    }
}

@Hidden
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
class MenuOption {
    @Schema(example = "1")
    private Long optionId;

    @Schema(example = "닭가슴살 추가")
    private String optionName;

    @Schema(example = "0")
    private int price;
    
    public MenuOptionSnapshot toEntity() {
        return MenuOptionSnapshot.builder()
        .optinoId(getOptionId())
        .optionName(getOptionName())
        .price(getPrice())
        .build();
    }
}