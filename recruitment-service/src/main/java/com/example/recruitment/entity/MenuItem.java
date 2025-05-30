package com.example.recruitment.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem {

    private String name;
    private int price;
}
