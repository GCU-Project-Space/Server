package com.example.recruitment.dto;

import com.example.recruitment.dto.order.OrderRequestDto;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class RecruitmentRequestDto {

    @NotNull(message = "userId는 필수입니다.")
    private Long userId;

    @NotNull(message = "storeId는 필수입니다.")
    private Long storeId;

    @NotBlank(message = "제목은 비어 있을 수 없습니다.")
    private String title;

    @Size(min = 10, message = "설명은 최소 10자 이상이어야 합니다.")
    private String description;

    @NotNull(message = "마감 시간은 필수입니다.")
    private LocalDateTime deadlineTime;

    @NotBlank(message = "카테고리는 비어 있을 수 없습니다.")
    private String category;

    // 주문용 메뉴 정보
    @NotNull(message = "메뉴 정보는 필수입니다.")
    @Size(min = 1, message = "최소 하나 이상의 메뉴를 선택해야 합니다.")
    private List<OrderRequestDto.MenuDto> menus;

    // OrderRequestDto 변환 메서드
    public OrderRequestDto toOrderRequestDto() {
        OrderRequestDto dto = new OrderRequestDto();
        dto.setUserId(this.userId);
        dto.setStoreId(this.storeId);
        dto.setMenus(this.menus);
        return dto;
    }
}
