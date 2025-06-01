package com.example.order_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.order_service.common.CustomException;
import com.example.order_service.common.ErrorCode;
import com.example.order_service.common.GroupStatus;
import com.example.order_service.common.OrderStatus;
import com.example.order_service.dto.request.GroupRequest;
import com.example.order_service.dto.request.OrderRequest;
import com.example.order_service.dto.response.GroupResponse;
import com.example.order_service.dto.response.OrderResponse;
import com.example.order_service.entity.GroupEntity;
import com.example.order_service.entity.OrderEntity;
import com.example.order_service.repository.GroupRepository;

import jakarta.transaction.Transactional;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final OrderService orderService;

    public GroupService(GroupRepository groupRepository, OrderService orderService) {
        this.groupRepository = groupRepository;
        this.orderService = orderService;
    }

    // 그룹 생성
    @Transactional
    public GroupResponse createGroup(GroupRequest request) {
        GroupEntity group = GroupEntity.builder()
                .leaderId(request.getLeaderId())
                .storeId(request.getStoreId())
                .title(request.getTitle())
                .description(request.getDescription())
                .status(GroupStatus.RECRUITING)
                .deadlineTime(request.getDeadlineTime())
                .category(request.getCategory())
                .build();

        GroupEntity savedGroup = groupRepository.save(group);
        
        // 리더의 주문 생성
        OrderRequest order = request.getGroupOrder().get(0);
        order.setGroupId(savedGroup.getId());
        order.setUserId(request.getLeaderId());
        OrderResponse orderResponse = orderService.createOrder(order);

        GroupResponse groupResponse = savedGroup.toResponse();
        groupResponse.getOrders().add(orderResponse);

        return groupResponse;
    }

    // 그룹 참가 (주문 추가)
    @Transactional
    public GroupResponse joinGroup(Long groupId, OrderRequest request) {
        GroupEntity group = groupRepository.findById(groupId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));

        if (group.getStatus() != GroupStatus.RECRUITING) {
            throw new CustomException(ErrorCode.BAD_REQUEST);
        }

        // OrderService를 통해 주문 생성
        orderService.createOrder(request);

        return group.toResponse();
    }

    // 전체 그룹 조회
    public List<GroupResponse> getAllGroups() {
        List<GroupEntity> groups = groupRepository.findAll();
        if (groups.isEmpty()) throw new CustomException(ErrorCode.NOT_FOUND);
        
        return groups.stream()
                .map(GroupEntity::toResponse)
                .collect(Collectors.toList());
    }

    // 카테고리별 그룹 조회
    public List<GroupResponse> getGroups(String category) {
        List<GroupEntity> groups = groupRepository.findByCategory(category);
        if (groups.isEmpty()) throw new CustomException(ErrorCode.NOT_FOUND);
        
        return groups.stream()
                .map(GroupEntity::toResponse)
                .collect(Collectors.toList());
    }

    // 가게별 그룹 조회
    public List<GroupResponse> getGroupsByStoreId(Long storeId) {
        List<GroupEntity> groups = groupRepository.findByStoreId(storeId);
        if (groups.isEmpty()) throw new CustomException(ErrorCode.NOT_FOUND);
        
        return groups.stream()
                .filter(group -> group.getStatus() == GroupStatus.SUBMITTED)
                .map(GroupEntity::toResponse)
                .collect(Collectors.toList());
    }

    // 그룹 상세 조회
    public GroupResponse getGroupDetail(Long groupId) {
        GroupEntity group = groupRepository.findById(groupId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));
        
        return group.toResponse();
    }

    // 사용자별 참여 그룹 조회
    public List<GroupResponse> getUserGroups(Long userId) {
        List<GroupEntity> groups = groupRepository.findGroupsByUserId(userId);
        if (groups.isEmpty()) throw new CustomException(ErrorCode.NOT_FOUND);
        
        return groups.stream()
                .map(GroupEntity::toResponse)
                .collect(Collectors.toList());
    }

    // 주문 전송
    @Transactional
    public GroupResponse submitOrder(Long groupId) {
        GroupEntity group = groupRepository.findById(groupId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));

        // 그룹 내 모든 주문이 PAID 상태인지 확인
        List<OrderEntity> orders = group.getGroupOrder();
        boolean allPaid = orders.stream()
                .allMatch(order -> order.getStatus() == OrderStatus.PAID);
        
        if (!allPaid) {
            throw new CustomException(ErrorCode.BAD_REQUEST);
        }

        group.setStatus(GroupStatus.SUBMITTED);
        GroupEntity savedGroup = groupRepository.save(group);
        
        return savedGroup.toResponse();
    }

    // 주문 수락
    @Transactional
    public GroupResponse acceptOrder(Long groupId) {
        GroupEntity group = groupRepository.findById(groupId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));

        group.setStatus(GroupStatus.CONFIRMED);
        GroupEntity savedGroup = groupRepository.save(group);
        
        return savedGroup.toResponse();
    }

    // 주문 완료
    @Transactional
    public GroupResponse completeOrder(Long groupId) {
        GroupEntity group = groupRepository.findById(groupId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));

        group.setStatus(GroupStatus.COMPLETED);
        GroupEntity savedGroup = groupRepository.save(group);
        
        orderService.completeOrdersByGroupId(groupId);

        return savedGroup.toResponse();
    }

    // 주문 거절
    @Transactional
    public GroupResponse rejectOrder(Long groupId) {
        GroupEntity group = groupRepository.findById(groupId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));

        group.setStatus(GroupStatus.REJECTED);
        GroupEntity savedGroup = groupRepository.save(group);
        
        return savedGroup.toResponse();
    }

    // 단체 주문 취소
    @Transactional
    public void cancelGroup(Long groupId) {
        GroupEntity group = groupRepository.findById(groupId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));

        // 그룹의 모든 주문 취소
        orderService.cancelOrdersByGroupId(groupId);
        
        group.setStatus(GroupStatus.CANCELED);
        groupRepository.save(group);
    }
}