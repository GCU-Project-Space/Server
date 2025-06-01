package com.example.order_service.dto.request;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.order_service.entity.GroupEntity;

import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GroupRequest {

    private Long leaderId;

    private Long storeId;

    private String title;

    private String description;

    private LocalDateTime deadlineTime;

    private String category;

    @ElementCollection
    @Builder.Default
    private List<OrderRequest> groupOrder = new ArrayList<>();

    public GroupEntity toEntity() {
        return GroupEntity.builder().build();
    }
}
