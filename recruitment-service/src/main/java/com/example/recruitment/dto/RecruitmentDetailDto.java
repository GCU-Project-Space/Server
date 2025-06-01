package com.example.recruitment.dto;

import com.example.recruitment.entity.Recruitment;
import com.example.recruitment.entity.Store;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class RecruitmentDetailDto {

    private Long id;
    private String title;
    private String description;
    private String status;
    private LocalDateTime deadlineTime;

    private UserDto user;
    private StoreDto store;
    private List<UserDto> participants;
    private List<Long> orderIds;

    public RecruitmentDetailDto(
            Recruitment recruitment,
            UserDto writer,
            List<UserDto> participantUsers,
            List<Long> orderIds
    ) {
        this.id = recruitment.getId();
        this.title = recruitment.getTitle();
        this.description = recruitment.getDescription();
        this.status = recruitment.getStatus();
        this.deadlineTime = recruitment.getDeadlineTime();
        this.user = writer;  // ✅ 유저 서비스로부터 가져온 작성자 정보
        this.store = new StoreDto(recruitment.getStore());
        this.participants = participantUsers;
        this.orderIds = orderIds;
    }

    @Getter
    @Setter
    public static class UserDto {
        private Long id;
        private String name;
        private String email;

        public UserDto(Long id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
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
