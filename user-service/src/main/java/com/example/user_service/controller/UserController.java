package com.example.user_service.controller;

import org.springframework.web.bind.annotation.*;  
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.example.user_service.DTO.UserRequestDTO;
import com.example.user_service.DTO.UserResponseDTO;
import com.example.user_service.DTO.UserUpdateRequestDTO;
import com.example.user_service.ApiResponse;
import com.example.user_service.DTO.UserLoginRequestDTO;
import com.example.user_service.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<UserResponseDTO>> signup(@RequestBody UserRequestDTO requestDTO) {
        UserResponseDTO responseDTO = userService.createUser(requestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.created(responseDTO, "회원가입 성공"));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserResponseDTO>> login(@RequestBody UserLoginRequestDTO requestDTO) {
        UserResponseDTO responseDTO = userService.login(requestDTO);

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(responseDTO, "로그인 성공"));
    }

    // 유저 정보 조회
    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserResponseDTO>> getUser(@PathVariable Long userId){
        UserResponseDTO responseDTO = userService.getUserById(userId);
        
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(responseDTO, "유저 정보 조회 성공"));
    }

    // 유저 정보 수정
    @PatchMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserResponseDTO>> updateUser(@PathVariable Long userId, @RequestBody UserUpdateRequestDTO requestDTO){
        UserResponseDTO responseDTO = userService.updateUser(userId, requestDTO);

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(responseDTO, "유저 정보 수정 성공"));
    }
}