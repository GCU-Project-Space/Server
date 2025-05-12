package com.example.order_service.dto.response;

import java.util.List;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Hidden
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class MenuResponse {
    
    @Schema(example = "맛있는 치킨")
    private String menuName;

    @Schema(example = "1")
    private int count;

    @Schema(example = "1000000")
    private int basePrice;

    @Schema(example = "1000000")
    private int totalPrice;
    
    private List<MenuOptionResponse> options;

}
