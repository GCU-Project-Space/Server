package com.example.order_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.order_service.common.ResponseBody;
import com.example.order_service.dto.request.GroupRequest;
import com.example.order_service.dto.request.OrderRequest;
import com.example.order_service.dto.response.GroupResponse;
import com.example.order_service.service.GroupService;

@RestController
@RequestMapping("/api/v1/recruitments")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    // Create
    // 그룹 생성 (그룹 생성 하고 주문 추가)
    @PostMapping
    public ResponseEntity<ResponseBody<GroupResponse>> createGroup(@RequestBody GroupRequest request) {
        
        GroupResponse response = groupService.createGroup(request);

        return ResponseEntity.ok(ResponseBody.created(response, "그룹 생성에 성공했습니다."));
    }

    // 그룹 참가 (그룹에 주문 추가)
    @PostMapping("/{groupId}/orders")
    public ResponseEntity<ResponseBody<GroupResponse>> joinGroup(
            @PathVariable Long groupId, 
            @RequestBody OrderRequest request) {
        
        GroupResponse response = groupService.joinGroup(groupId, request);
        
        return ResponseEntity.ok(ResponseBody.created(response, "그룹 참가에 성공했습니다."));
    }
    
    // Read
    // 모든 그룹 조회
    @GetMapping
    public ResponseEntity<ResponseBody<List<GroupResponse>>> getAllGroups() {
        
        List<GroupResponse> response = groupService.getAllGroups();
        
        return ResponseEntity.ok(ResponseBody.created(response, "전체 그룹 조회에 성공했습니다."));
    }

    // 카테고리별 그룹 리스트 조회
    @GetMapping("/category/{category}")
    public ResponseEntity<ResponseBody<List<GroupResponse>>> getGroups(@PathVariable String category) {

        List<GroupResponse> response = groupService.getGroups(category);

        return ResponseEntity.ok(ResponseBody.created(response, "조회에 성공했습니다."));
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<ResponseBody<List<GroupResponse>>> getGroupsByStoreId(@PathVariable Long storeId) {
        
        List<GroupResponse> response = groupService.getGroupsByStoreId(storeId);
        
        return ResponseEntity.ok(ResponseBody.created(response, "가게별 그룹 조회에 성공했습니다."));
    }

    // 그룹id로 그룹 상세 조회
    @GetMapping("/{groupId}")
    public ResponseEntity<ResponseBody<GroupResponse>> getGroupDetail(@PathVariable Long groupId) {
        
        GroupResponse response = groupService.getGroupDetail(groupId);
        
        return ResponseEntity.ok(ResponseBody.created(response, "그룹 상세 조회에 성공했습니다."));
    }
    
    // 유저id로 현재 참여하고 있는 그룹 조회
    @GetMapping("/user/{userId}")
    public ResponseEntity<ResponseBody<List<GroupResponse>>> getUserGroups(@PathVariable Long userId) {
        
        List<GroupResponse> response = groupService.getUserGroups(userId);
        
        return ResponseEntity.ok(ResponseBody.created(response, "사용자 참여 그룹 조회에 성공했습니다."));
    }

    // Update
    // 주문 전송
    @PutMapping("/{groupId}/submit")
    public ResponseEntity<ResponseBody<GroupResponse>> submitOrder(@PathVariable Long groupId) {
        
        GroupResponse response = groupService.submitOrder(groupId);
        
        return ResponseEntity.ok(ResponseBody.created(response, "주문 전송에 성공했습니다."));
    }
    
    // 주문 수락
    @PutMapping("/{groupId}/accept")
    public ResponseEntity<ResponseBody<GroupResponse>> acceptOrder(@PathVariable Long groupId) {
        
        GroupResponse response = groupService.acceptOrder(groupId);
        
        return ResponseEntity.ok(ResponseBody.created(response, "주문 수락에 성공했습니다."));
    }

    // 주문 거절
    @PutMapping("/{groupId}/reject")
    public ResponseEntity<ResponseBody<GroupResponse>> rejectOrder(@PathVariable Long groupId) {
        
        GroupResponse response = groupService.rejectOrder(groupId);
        
        return ResponseEntity.ok(ResponseBody.created(response, "주문 거절에 성공했습니다."));
    }

    // 주문 완료
    @PutMapping("/{groupId}/complete")
    public ResponseEntity<ResponseBody<GroupResponse>> completeOrder(@PathVariable Long groupId) {
        
        GroupResponse response = groupService.completeOrder(groupId);
        
        return ResponseEntity.ok(ResponseBody.created(response, "주문이 성공적으로 종료되었습니다."));
    }
    
    // Delete
    // 단체 주문 취소
    @DeleteMapping("/{groupId}")
    public ResponseEntity<ResponseBody<Void>> cancelGroup(@PathVariable Long groupId) {
        
        groupService.cancelGroup(groupId);
        
        return ResponseEntity.ok(ResponseBody.created(null, "단체 주문 취소에 성공했습니다."));
    }
}