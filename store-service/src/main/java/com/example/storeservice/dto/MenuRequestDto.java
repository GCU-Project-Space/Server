package com.example.storeservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.util.Map;


@Getter
@Setter
@NoArgsConstructor
public class MenuRequestDto {

    @Schema(description = "메뉴 이름", example = "참치마요덮밥")
    @NotBlank(message = "메뉴 이름은 필수입니다.")
    private String name;

    @Schema(description = "메뉴 가격 (원)", example = "7500")
    @Min(value = 0, message = "가격은 0원 이상이어야 합니다.")
    private int price;

    @Schema(description = "메뉴 설명", example = "고소하고 담백한 참치마요 덮밥")
    @NotBlank(message = "메뉴 설명은 필수입니다.")
    private String description;

    @Schema(
        description = "메뉴 옵션",
        example = "{\"맛\": [\"순한맛\", \"매운맛\"], \"사이즈\": [\"보통\", \"곱빼기\"]}"
    )
    private Map<String, List<String>> options;

    @Schema(description = "메뉴 이미지 URL", example = "https://cdn.example.com/images/menu1.jpg")
    private String imageUrl;

    @Schema(description = "등록할 가게 ID", example = "1", required = true)
    @NotNull(message = "storeId는 필수입니다.")
    private Long storeId;
}

