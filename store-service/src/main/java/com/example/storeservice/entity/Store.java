package com.example.storeservice.entity;

import jakarta.persistence.*;
import lombok.*;

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
    private int minOrderPrice;

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getLocation() { return location; }
    public String getDescription() { return description; }
    public String getOpenHours() { return openHours; }
    public int getMinOrderPrice() { return minOrderPrice; }

    public void setName(String name) { this.name = name; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setLocation(String location) { this.location = location; }
    public void setDescription(String description) { this.description = description; }
    public void setOpenHours(String openHours) { this.openHours = openHours; }
    public void setMinOrderPrice(int minOrderPrice) { this.minOrderPrice = minOrderPrice; }
}
