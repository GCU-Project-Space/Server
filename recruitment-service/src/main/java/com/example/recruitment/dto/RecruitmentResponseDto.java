package com.example.recruitment.dto;

import com.example.recruitment.entity.Recruitment;
import com.example.recruitment.entity.Store;
import com.example.recruitment.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RecruitmentResponseDto {

    private Long id;
    private String title;
    private String description;
    private String status;
    private LocalDateTime deadlineTime;

    private UserDto user;
    private StoreDto store;

    public RecruitmentResponseDto(Recruitment recruitment) {
        this.id = recruitment.getId();
        this.title = recruitment.getTitle();
        this.description = recruitment.getDescription();
        this.status = recruitment.getStatus();
        this.deadlineTime = recruitment.getDeadlineTime();
        this.user = new UserDto(recruitment.getUser());
        this.store = new StoreDto(recruitment.getStore());
    }

    @Getter
    @Setter
    public static class UserDto {
        private Long id;
        private String name;
        private String email;

        public UserDto(User user) {
            this.id = user.getId();
            this.name = user.getName();
            this.email = user.getEmail();
        }
    }

    @Getter
    @Setter
    public static class StoreDto {
        private Long id;
        private String name;
        private String category;
        private String location;

        public StoreDto(Store store) {
            this.id = store.getId();
            this.name = store.getName();
            this.category = store.getCategory();
            this.location = store.getLocation();
        }
    }
}
