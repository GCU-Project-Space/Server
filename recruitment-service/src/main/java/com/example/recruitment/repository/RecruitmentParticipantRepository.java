package com.example.recruitment.repository;

import com.example.recruitment.entity.RecruitmentParticipant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecruitmentParticipantRepository extends JpaRepository<RecruitmentParticipant, Long> {
     // 특정 유저가 특정 모집글에 이미 참여했는지 확인
    Optional<RecruitmentParticipant> findByUserIdAndRecruitmentId(Long userId, Long recruitmentId);

     // 특정 모집글에 참여한 인원 수 카운트
    long countByRecruitmentId(Long recruitmentId);

    List<RecruitmentParticipant> findByRecruitmentId(Long recruitmentId);
    List<RecruitmentParticipant> findByUserId(Long userId);



}
