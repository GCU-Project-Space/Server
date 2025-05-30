package com.example.storeservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.ArrayList;

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
    @CollectionTable(name = "menu_options", joinColumns = @JoinColumn(name = "menu_id"))
    @OrderColumn(name = "options_index")
    private List<OptionInfo> options = new ArrayList<>();
    

    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @OneToOne(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
    private MenuDiscount menuDiscount;

    @Embeddable
    @Getter @Setter
    @NoArgsConstructor
    public static class OptionInfo {
        private String id;
        private String name;
        private Integer price;
    }
}
