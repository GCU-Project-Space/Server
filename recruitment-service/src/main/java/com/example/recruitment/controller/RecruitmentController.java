package com.example.recruitment.controller;

import com.example.recruitment.common.ApiResponse;
import com.example.recruitment.dto.RecruitmentDetailDto;
import com.example.recruitment.dto.RecruitmentRequestDto;
import com.example.recruitment.dto.RecruitmentResponseDto;
import com.example.recruitment.dto.order.OrderRequestDto;
import com.example.recruitment.entity.Recruitment;
import com.example.recruitment.entity.RecruitmentParticipant;
import com.example.recruitment.entity.Store;
import com.example.recruitment.exception.CustomException;
import com.example.recruitment.exception.ErrorCode;
import com.example.recruitment.repository.RecruitmentParticipantRepository;
import com.example.recruitment.repository.RecruitmentRepository;
import com.example.recruitment.repository.StoreRepository;
import com.example.recruitment.service.RecruitmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/recruitments")
public class RecruitmentController {

    private final RecruitmentService recruitmentService;
    private final RecruitmentRepository recruitmentRepository;
    private final RecruitmentParticipantRepository participantRepository;
    private final StoreRepository storeRepository;

    @PostMapping
    public ResponseEntity<ApiResponse<Map<String, Long>>> createRecruitment(@Valid @RequestBody RecruitmentRequestDto dto) {
        Long recruitmentId = recruitmentService.createRecruitment(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.created(Map.of("recruitmentId", recruitmentId), "모집글 생성 완료"));
    }

    @PostMapping("/{recruitmentId}/join")
    public ResponseEntity<ApiResponse<String>> joinRecruitment(@PathVariable Long recruitmentId,
                                                                @RequestParam Long userId,
                                                                @Valid @RequestBody OrderRequestDto orderRequestDto) {
        recruitmentService.joinRecruitment(recruitmentId, userId, orderRequestDto);
        return ResponseEntity.ok(ApiResponse.ok(null, "모집글 참여 완료"));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<RecruitmentResponseDto>>> getAll() {
        List<Recruitment> recruitments = recruitmentRepository.findAll();
        List<RecruitmentResponseDto> response = recruitments.stream()
                .map(RecruitmentResponseDto::new)
                .toList();
        return ResponseEntity.ok(ApiResponse.ok(response, "모든 모집글 조회 성공"));
    }

    @GetMapping(params = "status")
    public ResponseEntity<ApiResponse<List<RecruitmentResponseDto>>> getByStatus(@RequestParam String status) {
        List<Recruitment> recruitments = recruitmentRepository.findByStatus(status);
        List<RecruitmentResponseDto> response = recruitments.stream()
                .map(RecruitmentResponseDto::new)
                .toList();
        return ResponseEntity.ok(ApiResponse.ok(response, "상태별 모집글 조회 성공"));
    }

    @GetMapping("/{recruitmentId}")
    public ResponseEntity<ApiResponse<RecruitmentDetailDto>> getRecruitmentDetail(@PathVariable Long recruitmentId) {
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));

        List<RecruitmentParticipant> participants = participantRepository.findByRecruitmentId(recruitmentId);

        List<RecruitmentDetailDto.UserDto> participantUsers = participants.stream()
                .map(p -> new RecruitmentDetailDto.UserDto(p.getUser()))
                .toList();

        List<Long> orderIds = participants.stream()
                .map(RecruitmentParticipant::getOrderId)
                .toList();

        RecruitmentDetailDto dto = new RecruitmentDetailDto(recruitment, participantUsers, orderIds);

        return ResponseEntity.ok(ApiResponse.ok(dto, "모집 상세 조회 성공"));
    }

    @GetMapping("/user/{userId}/created-recruitments")
    public ResponseEntity<ApiResponse<List<RecruitmentResponseDto>>> getRecruitmentsCreatedByUser(@PathVariable Long userId) {
        List<Recruitment> list = recruitmentRepository.findByUserId(userId);
        List<RecruitmentResponseDto> response = list.stream()
                .map(RecruitmentResponseDto::new)
                .toList();
        return ResponseEntity.ok(ApiResponse.ok(response, "작성한 모집글 조회 성공"));
    }

    @GetMapping("/user/{userId}/joined-recruitments")
    public ResponseEntity<ApiResponse<List<RecruitmentResponseDto>>> getRecruitmentsJoinedByUser(@PathVariable Long userId) {
        List<RecruitmentParticipant> participantList = participantRepository.findByUserId(userId);
        List<RecruitmentResponseDto> response = participantList.stream()
                .map(RecruitmentParticipant::getRecruitment)
                .map(RecruitmentResponseDto::new)
                .toList();
        return ResponseEntity.ok(ApiResponse.ok(response, "참여한 모집글 조회 성공"));
    }

    @PatchMapping("/{recruitmentId}/status")
    public ResponseEntity<ApiResponse<String>> updateRecruitmentStatus(@PathVariable Long recruitmentId) {
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));

        LocalDateTime now = LocalDateTime.now();
        long participantCount = participantRepository.countByRecruitmentId(recruitmentId);

        if (now.isAfter(recruitment.getDeadlineTime())) {
            if (participantCount >= 3) {
                recruitment.setStatus("CONFIRMED");
            } else {
                recruitment.setStatus("FAILED");
            }
            recruitmentRepository.save(recruitment);
            return ResponseEntity.ok(ApiResponse.ok(null, "상태가 " + recruitment.getStatus() + "로 변경되었습니다."));
        } else {
            return ResponseEntity.ok(ApiResponse.ok(null, "아직 마감 시간이 지나지 않았습니다."));
        }
    }

    @PatchMapping("/{recruitmentId}/accept")
    public ResponseEntity<ApiResponse<String>> acceptRecruitment(@PathVariable Long recruitmentId) {
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));

        if (!"CONFIRMED".equals(recruitment.getStatus())) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.fail(4001, "주문 수락은 CONFIRMED 상태에서만 가능합니다.", HttpStatus.BAD_REQUEST));
        }
        recruitment.setStatus("ACCEPTED");
        recruitmentRepository.save(recruitment);
        return ResponseEntity.ok(ApiResponse.ok(null, "상태가 ACCEPTED로 변경되었습니다."));
    }

    @PatchMapping("/{recruitmentId}/deliver")
    public ResponseEntity<ApiResponse<String>> completeDelivery(@PathVariable Long recruitmentId) {
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));

        if (!"ACCEPTED".equals(recruitment.getStatus())) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.fail(4002, "배달 완료는 ACCEPTED 상태에서만 가능합니다.", HttpStatus.BAD_REQUEST));
        }
        recruitment.setStatus("DELIVERED");
        recruitmentRepository.save(recruitment);
        return ResponseEntity.ok(ApiResponse.ok(null, "상태가 DELIVERED로 변경되었습니다."));
    }

    @DeleteMapping("/{recruitmentId}")
    public ResponseEntity<ApiResponse<String>> deleteRecruitment(@PathVariable Long recruitmentId) {
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));
        recruitmentRepository.delete(recruitment);
        return ResponseEntity.ok(ApiResponse.ok(null, "모집글이 삭제되었습니다."));
    }

    @PutMapping("/{recruitmentId}")
    public ResponseEntity<ApiResponse<String>> updateRecruitment(@PathVariable Long recruitmentId,
                                                                  @Valid @RequestBody RecruitmentRequestDto dto) {
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));

        if (!recruitment.getUser().getId().equals(dto.getUserId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(ApiResponse.fail(4031, "작성자만 수정할 수 있습니다.", HttpStatus.FORBIDDEN));
        }

        recruitment.setTitle(dto.getTitle());
        recruitment.setDescription(dto.getDescription());
        recruitment.setDeadlineTime(dto.getDeadlineTime());

        if (dto.getStoreId() != null) {
            Store store = storeRepository.findById(dto.getStoreId())
                    .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));
            recruitment.setStore(store);
        }

        recruitmentRepository.save(recruitment);
        return ResponseEntity.ok(ApiResponse.ok(null, "모집글이 수정되었습니다."));
    }
}
