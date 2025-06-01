package com.example.order_service.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupResponse {
    
    private Long groupId;

    private Long leaderId;

    private Long storeId;

    private String title;

    private String description;

    private String status;

    private String category;

    private LocalDateTime deadlineTime;

    private List<OrderResponse> orders;

}
