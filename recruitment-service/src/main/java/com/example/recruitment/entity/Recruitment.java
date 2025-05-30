package com.example.recruitment.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Recruitment {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Store store;

    @OneToMany(mappedBy = "recruitment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecruitmentParticipant> participants;

    private String title;
    private String description;

    private String status;  // RECRUITING, CONFIRMED 등

    private LocalDateTime deadlineTime;

    //카테고리 추가가
    @Column(nullable = false)
    private String category;

    @Column(name = "order_id")
    private Long orderId;

    @ElementCollection
    private List<Long> orderIds = new ArrayList<>();

    public void addOrderId(Long orderId) {
        this.orderIds.add(orderId);
}

}
