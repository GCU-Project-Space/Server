package com.example.recruitment.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Store {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private String category;
    private String location;
}
