package com.example.storeservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MenuRequestDto {

    @Schema(description = "메뉴 이름", example = "참치마요덮밥")
    @NotBlank(message = "메뉴 이름은 필수입니다.")
    private String name;

    @Schema(description = "메뉴 가격 (원)", example = "7500")
    @NotNull(message = "가격은 필수입니다.")
    @Min(value = 0, message = "가격은 0원 이상이어야 합니다.")
    private Integer price;

    @Schema(description = "메뉴 설명", example = "고소하고 담백한 참치마요 덮밥")
    @NotBlank(message = "메뉴 설명은 필수입니다.")
    private String description;

    @Schema(
        description = "메뉴 옵션",
        example = "[{\"id\": \"1\", \"name\": \"곱빼기\", \"price\": 2000}, {\"id\": \"2\", \"name\": \"매운맛\", \"price\": 1000}]"
    )
    private List<OptionInfo> options;

    @Schema(description = "이미지 URL", example = "https://example.com/image.jpg")
    private String imageUrl;

    @Getter @Setter
    @NoArgsConstructor
    public static class OptionInfo {
        @Schema(description = "옵션 ID", example = "1")
        @NotBlank(message = "옵션 ID는 필수입니다.")
        private String id;

        @Schema(description = "옵션 이름", example = "곱빼기")
        @NotBlank(message = "옵션 이름은 필수입니다.")
        private String name;

        @Schema(description = "옵션 가격", example = "2000")
        @NotNull(message = "옵션 가격은 필수입니다.")
        @Min(value = 0, message = "옵션 가격은 0원 이상이어야 합니다.")
        private Integer price;
    }
}