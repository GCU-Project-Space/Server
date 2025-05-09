package com.example.recruitment.repository;

import com.example.recruitment.entity.Recruitment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {
    List<Recruitment> findByStatus(String status);
    List<Recruitment> findByUserId(Long userId);

    
}
