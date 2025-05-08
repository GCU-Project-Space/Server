package com.example.order_service.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class MenuResponse {
    
    private String menuName;

    private int count;

    private int basePrice;

    private int totalPrice;
    
    private List<MenuOptionResponse> options;

}
