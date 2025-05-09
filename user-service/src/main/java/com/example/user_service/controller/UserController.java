package com.example.user_service.controller;

import org.springframework.web.bind.annotation.*;  
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.example.user_service.DTO.UserRequestDTO;
import com.example.user_service.DTO.UserResponseDTO;
import com.example.user_service.DTO.UserUpdateRequestDTO;
import com.example.user_service.DTO.UserLoginRequestDTO;
import com.example.user_service.DTO.UserLoginResponseDTO;
import com.example.user_service.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<UserResponseDTO> signup(@RequestBody UserRequestDTO requestDTO) {
        UserResponseDTO responseDTO = userService.createUser(requestDTO);
        
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDTO> login(@RequestBody UserLoginRequestDTO requestDTO) {
        UserLoginResponseDTO responseDTO = userService.login(requestDTO);

        return ResponseEntity.ok(responseDTO);
    }

    // 유저 정보 조회
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable Long userId){
        UserResponseDTO responseDTO = userService.getUserById(userId);
        
        return ResponseEntity.ok(responseDTO);
    }

    // 유저 정보 수정
    @PatchMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long userId, @RequestBody UserUpdateRequestDTO requestDTO){
        UserResponseDTO responseDTO = userService.updateUser(userId, requestDTO);

        return ResponseEntity.ok(responseDTO);
    }
}