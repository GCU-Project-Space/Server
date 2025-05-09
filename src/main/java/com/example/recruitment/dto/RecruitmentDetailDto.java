package com.example.recruitment.dto;

import com.example.recruitment.entity.Store;
import com.example.recruitment.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
public class RecruitmentDetailDto {
    private Long id;
    private String title;
    private String description;
    private String status;
    private LocalDateTime deadlineTime;
    private User user;
    private Store store;
    private List<User> participants;
}
