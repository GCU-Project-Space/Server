package com.example.recruitment.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Recruitment {

    @Id
    @GeneratedValue
    private Long id;

    // 작성자 (필수)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 가게 (필수)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    // 참여자 목록 (양방향 연관 관계)
    @OneToMany(mappedBy = "recruitment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecruitmentParticipant> participants = new ArrayList<>();

    // 모집글 정보
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String status;  // e.g., RECRUITING, CONFIRMED, FAILED

    @Column(nullable = false)
    private LocalDateTime deadlineTime;

    @Column(nullable = false)
    private String category;

    // 대표 주문 ID (모집자 주문)
    @Column(name = "order_id")
    private Long orderId;

    // 참여자 주문 ID 목록
    @ElementCollection
    private List<Long> orderIds = new ArrayList<>();

    public void addOrderId(Long orderId) {
        this.orderIds.add(orderId);
    }
}
