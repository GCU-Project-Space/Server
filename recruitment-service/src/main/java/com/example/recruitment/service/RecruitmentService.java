package com.example.recruitment.service;

import com.example.recruitment.dto.RecruitmentRequestDto;
import com.example.recruitment.dto.order.OrderRequestDto;
import com.example.recruitment.entity.Recruitment;
import com.example.recruitment.entity.RecruitmentParticipant;
import com.example.recruitment.entity.Store;
import com.example.recruitment.entity.User;
import com.example.recruitment.repository.RecruitmentParticipantRepository;
import com.example.recruitment.repository.RecruitmentRepository;
import com.example.recruitment.repository.StoreRepository;
import com.example.recruitment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final RecruitmentParticipantRepository participantRepository;
    private final OrderClient orderClient;

    /**
     * 모집글 생성 시:
     * - 사용자 및 가게 조회
     * - 모집글 저장
     * - 주문 서버에 주문 생성 요청
     * - 생성된 orderId를 모집글에 저장
     * - 모집자도 자동 참여자로 등록
     */
    @Transactional
    public Long createRecruitment(RecruitmentRequestDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("사용자 없음"));
        Store store = storeRepository.findById(dto.getStoreId())
                .orElseThrow(() -> new RuntimeException("가게 없음"));

        // 모집글 저장
        Recruitment recruitment = new Recruitment();
        recruitment.setUser(user);
        recruitment.setStore(store);
        recruitment.setTitle(dto.getTitle());
        recruitment.setDescription(dto.getDescription());
        recruitment.setDeadlineTime(dto.getDeadlineTime());
        recruitment.setStatus("RECRUITING");
        recruitment.setCategory(dto.getCategory());
        recruitmentRepository.save(recruitment); // ID 생성됨

        // 주문 서버에 주문 생성 요청
        OrderRequestDto orderDto = dto.toOrderRequestDto();
        orderDto.setGroupId(recruitment.getId());
        Long orderId = orderClient.createOrder(orderDto);

        // 모집글에 orderId 저장
        recruitment.setOrderId(orderId);
        recruitmentRepository.save(recruitment);

        // 모집자도 자동으로 참여자로 등록
        RecruitmentParticipant participant = new RecruitmentParticipant();
        participant.setRecruitment(recruitment);
        participant.setUser(user);
        participant.setOrderId(orderId);
        participantRepository.save(participant);

        return recruitment.getId();
    }

    /**
     * 모집글 참여 시:
     * - 사용자 및 모집글 조회
     * - 중복 참여 여부 확인
     * - 주문 서버에 주문 생성 요청
     * - 생성된 orderId를 RecruitmentParticipant에 저장
     */
    @Transactional
    public void joinRecruitment(Long recruitmentId, Long userId, OrderRequestDto orderDto) {
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
                .orElseThrow(() -> new RuntimeException("모집 없음"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자 없음"));

        boolean alreadyJoined = participantRepository.existsByRecruitmentAndUser(recruitment, user);
        if (alreadyJoined) {
            throw new RuntimeException("이미 참여한 모집입니다.");
        }

        // 주문 생성
        orderDto.setGroupId(recruitmentId);
        orderDto.setUserId(userId);
        Long orderId = orderClient.createOrder(orderDto);

        // 참여자 등록
        RecruitmentParticipant participant = new RecruitmentParticipant();
        participant.setRecruitment(recruitment);
        participant.setUser(user);
        participant.setOrderId(orderId);
        participantRepository.save(participant);
    }
}
