package com.example.order_service.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.order_service.dto.response.MenuResponse;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class MenuSnapshot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sId; // Surrogate Key

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

    private Long menuId;

    private String menuName;

    private int basePrice;

    private int count;

    private int totalPrice;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<MenuOptionSnapshot> options = new ArrayList<>(); 

    public MenuResponse toResponse() {
        return MenuResponse.builder()
        .menuName(getMenuName())
        .basePrice(getBasePrice())
        .count(getCount())
        .totalPrice(getTotalPrice())
        .options(getOptions().stream()
            .map(item -> item.toResponse())
            .collect(Collectors.toList())
        )
        .build();
    }

    // 연관관계 편의 메서드
    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public int calculateTotalPrice() {
        int optionsTotal = options.stream()
            .mapToInt(MenuOptionSnapshot::getPrice)
            .sum();
        setTotalPrice((basePrice + optionsTotal) * count);
        return getTotalPrice();
    }
}
