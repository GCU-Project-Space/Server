package com.example.recruitment.controller;

import com.example.recruitment.dto.RecruitmentDetailDto;
import com.example.recruitment.dto.RecruitmentRequestDto;
import com.example.recruitment.dto.order.OrderRequestDto;
import com.example.recruitment.entity.Recruitment;
import com.example.recruitment.entity.RecruitmentParticipant;
import com.example.recruitment.entity.User;
import com.example.recruitment.repository.RecruitmentParticipantRepository;
import com.example.recruitment.repository.RecruitmentRepository;
import com.example.recruitment.service.RecruitmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/recruitments")
public class RecruitmentController {

    private final RecruitmentService recruitmentService;
    private final RecruitmentRepository recruitmentRepository;
    private final RecruitmentParticipantRepository participantRepository;

    //모집글 생성 (Order 서버에 주문 생성 포함)
    @PostMapping
    public ResponseEntity<?> createRecruitment(@Valid @RequestBody RecruitmentRequestDto dto) {
        recruitmentService.createRecruitment(dto);
        return ResponseEntity.ok("모집글 생성 완료");
    }

    //모집글 참여 (Order 서버에 주문 생성 포함)
    @PostMapping("/{recruitmentId}/join")
    public ResponseEntity<?> joinRecruitment(@PathVariable Long recruitmentId,
                                             @RequestParam Long userId,
                                             @RequestBody OrderRequestDto orderRequestDto) {
        recruitmentService.joinRecruitment(recruitmentId, userId, orderRequestDto);
        return ResponseEntity.ok("모집글 참여 완료");
    }

    // 모집글 전체 조회
    @GetMapping
    public List<Recruitment> getAll() {
        return recruitmentRepository.findAll();
    }

    // 상태별 조회
    @GetMapping(params = "status")
    public List<Recruitment> getByStatus(@RequestParam String status) {
        return recruitmentRepository.findByStatus(status);
    }

    // 모집글 상세 조회
    @GetMapping("/{recruitmentId}")
    public ResponseEntity<?> getRecruitmentDetail(@PathVariable Long recruitmentId) {
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId).orElseThrow();

        List<RecruitmentParticipant> participants =
                participantRepository.findByRecruitmentId(recruitmentId);
        List<User> participantUsers = participants.stream()
                .map(RecruitmentParticipant::getUser)
                .toList();

        RecruitmentDetailDto dto = new RecruitmentDetailDto();
        dto.setId(recruitment.getId());
        dto.setTitle(recruitment.getTitle());
        dto.setDescription(recruitment.getDescription());
        dto.setStatus(recruitment.getStatus());
        dto.setDeadlineTime(recruitment.getDeadlineTime());
        dto.setUser(recruitment.getUser());
        dto.setStore(recruitment.getStore());
        dto.setParticipants(participantUsers);

        return ResponseEntity.ok(dto);
    }

    // 유저가 만든 모집글
    @GetMapping("/user/{userId}/created-recruitments")
    public List<Recruitment> getRecruitmentsCreatedByUser(@PathVariable Long userId) {
        return recruitmentRepository.findByUserId(userId);
    }

    // 유저가 참여한 모집글
    @GetMapping("/user/{userId}/joined-recruitments")
    public List<Recruitment> getRecruitmentsJoinedByUser(@PathVariable Long userId) {
        List<RecruitmentParticipant> participantList = participantRepository.findByUserId(userId);
        return participantList.stream()
                .map(RecruitmentParticipant::getRecruitment)
                .toList();
    }

    // 모집 상태 업데이트
    @PatchMapping("/{recruitmentId}/status")
    public ResponseEntity<?> updateRecruitmentStatus(@PathVariable Long recruitmentId) {
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId).orElseThrow();
        LocalDateTime now = LocalDateTime.now();
        long participantCount = participantRepository.countByRecruitmentId(recruitmentId);

        if (now.isAfter(recruitment.getDeadlineTime())) {
            if (participantCount >= 3) {
                recruitment.setStatus("CONFIRMED");
            } else {
                recruitment.setStatus("FAILED");
            }
            recruitmentRepository.save(recruitment);
            return ResponseEntity.ok("상태가 " + recruitment.getStatus() + "로 변경되었습니다.");
        } else {
            return ResponseEntity.ok("아직 마감 시간이 지나지 않았습니다.");
        }
    }

    // 주문 수락 상태 변경
    @PatchMapping("/{recruitmentId}/accept")
    public ResponseEntity<?> acceptRecruitment(@PathVariable Long recruitmentId) {
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId).orElseThrow();
        if (!"CONFIRMED".equals(recruitment.getStatus())) {
            return ResponseEntity.badRequest().body("주문 수락은 CONFIRMED 상태에서만 가능합니다.");
        }
        recruitment.setStatus("ACCEPTED");
        recruitmentRepository.save(recruitment);
        return ResponseEntity.ok("상태가 ACCEPTED로 변경되었습니다.");
    }

    // 배달 완료 상태 변경
    @PatchMapping("/{recruitmentId}/deliver")
    public ResponseEntity<?> completeDelivery(@PathVariable Long recruitmentId) {
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId).orElseThrow();
        if (!"ACCEPTED".equals(recruitment.getStatus())) {
            return ResponseEntity.badRequest().body("배달 완료는 ACCEPTED 상태에서만 가능합니다.");
        }
        recruitment.setStatus("DELIVERED");
        recruitmentRepository.save(recruitment);
        return ResponseEntity.ok("상태가 DELIVERED로 변경되었습니다.");
    }

    // 모집글 삭제
    @DeleteMapping("/{recruitmentId}")
    public ResponseEntity<?> deleteRecruitment(@PathVariable Long recruitmentId) {
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId).orElseThrow();
        recruitmentRepository.delete(recruitment);
        return ResponseEntity.ok("모집글이 삭제되었습니다.");
    }

    // 모집글 수정
    @PutMapping("/{recruitmentId}")
    public ResponseEntity<?> updateRecruitment(@PathVariable Long recruitmentId,
                                               @Valid @RequestBody RecruitmentRequestDto dto) {
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId).orElseThrow();
        if (!recruitment.getUser().getId().equals(dto.getUserId())) {
            return ResponseEntity.status(403).body("작성자만 수정할 수 있습니다.");
        }

        recruitment.setTitle(dto.getTitle());
        recruitment.setDescription(dto.getDescription());
        recruitment.setDeadlineTime(dto.getDeadlineTime());

        if (dto.getStoreId() != null) {
            recruitment.setStore(recruitment.getStore());
        }

        recruitmentRepository.save(recruitment);
        return ResponseEntity.ok("모집글이 수정되었습니다.");
    }
}
