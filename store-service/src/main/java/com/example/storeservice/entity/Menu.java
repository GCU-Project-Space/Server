package com.example.storeservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 단일 PK로 사용

    private String name;
    private int price;
    private String description;

    @ElementCollection
    private Map<String, List<String>> options = new HashMap<>();


    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;
}
