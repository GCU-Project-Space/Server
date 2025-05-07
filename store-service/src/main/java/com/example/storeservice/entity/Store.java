package com.example.storeservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phone;
    private String location;
    private String description;
    private String openHours;
    private int minOrderPrice;

    public Store(String name, String phone, String location, String description, String openHours, int minOrderPrice) {
        this.name = name;
        this.phone = phone;
        this.location = location;
        this.description = description;
        this.openHours = openHours;
        this.minOrderPrice = minOrderPrice;
    }
}
