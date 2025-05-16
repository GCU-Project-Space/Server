package com.example.order_service.dto.response;

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
public class MenuOptionResponse {
    @Schema(example = "닭가슴살 추가")
    private String optionName;

    @Schema(example = "10000")
    private int price;

}
