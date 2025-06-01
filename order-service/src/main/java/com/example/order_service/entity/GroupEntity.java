package com.example.order_service.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.annotations.SQLRestriction;

import com.example.order_service.common.GroupStatus;
import com.example.order_service.dto.response.GroupResponse;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class GroupEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long leaderId;

    @Column(nullable = false)
    private Long storeId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Column(nullable = false)
    private GroupStatus status = GroupStatus.RECRUITING;  // e.g., RECRUITING, CONFIRMED, FAILED

    @Column(nullable = false)
    private LocalDateTime deadlineTime;

    @Column(nullable = false)
    private String category;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "groupId")
    @SQLRestriction("status <> 'CANCELED'")
    @Builder.Default
    private List<OrderEntity> groupOrder = new ArrayList<>();

    public GroupResponse toResponse() {
        return GroupResponse.builder()
            .groupId(this.id)
            .leaderId(this.leaderId)
            .storeId(this.storeId)
            .title(this.title)
            .description(this.description)
            .status(this.status.name())
            .category(this.category)
            .deadlineTime(this.deadlineTime)
            .orders(this.groupOrder.stream()
                .map(OrderEntity::toResponse)
                .collect(Collectors.toList()))
            .build();
    } 
}

