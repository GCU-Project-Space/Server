package com.example.order_service.entity;

import com.example.order_service.dto.response.MenuOptionResponse;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// @Embeddable
// @NoArgsConstructor
// @AllArgsConstructor
// @Getter
// @Setter
// class OrderItemOptionSnapshotId implements Serializable {
//     private Long orderId;
//     private Long orderItemId;
//     private Long orderItemOptionId;
// }

@Hidden
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MenuOptionSnapshot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sId; // Surrogate Key

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    private MenuSnapshot menu;
    
    private Long optinoId;

    private String optionName;

    private int price;

    public MenuOptionResponse toResponse() {
        return MenuOptionResponse.builder()
        .optionName(getOptionName())
        .price(getPrice())
        .build();
    }

    // 연관관계 편의 메서드
    public void setMenu(MenuSnapshot menu) {
        this.menu = menu;
    }
}
