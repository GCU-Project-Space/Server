package com.example.storeservice.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
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
    @Column(nullable = false)
    private int minOrderPrice;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Menu> menus = new ArrayList<>();

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getLocation() { return location; }
    public String getDescription() { return description; }
    public String getOpenHours() { return openHours; }
    public int getMinOrderPrice() { return minOrderPrice; }
    public Category getCategory() { return category; }

    public void setName(String name) { this.name = name; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setLocation(String location) { this.location = location; }
    public void setDescription(String description) { this.description = description; }
    public void setOpenHours(String openHours) { this.openHours = openHours; }
    public void setMinOrderPrice(int minOrderPrice) { this.minOrderPrice = minOrderPrice; }
    public void setCategory(Category category) { this.category = category; }
}
