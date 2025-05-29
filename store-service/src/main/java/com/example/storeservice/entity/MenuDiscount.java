package com.example.storeservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter @Setter
@NoArgsConstructor
public class MenuDiscount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    @Column(nullable = false)
    private int discountRate;  // ex: 20 (20%)
}
