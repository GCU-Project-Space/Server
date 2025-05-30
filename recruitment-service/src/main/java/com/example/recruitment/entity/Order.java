package com.example.recruitment.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Recruitment recruitment; // 어떤 모집글에 대한 주문인지

    @ManyToOne
    private User user; // 누가 주문했는지

    @ElementCollection
    @CollectionTable(name = "order_menu_items", joinColumns = @JoinColumn(name = "order_id"))
    private List<MenuItem> menuItems; // 메뉴 리스트 (내장 객체로 구성)

    private int totalPrice; // 총 가격
}
