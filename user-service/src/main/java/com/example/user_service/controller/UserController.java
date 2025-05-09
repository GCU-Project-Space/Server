package com.example.user_service.controller;

import org.springframework.web.bind.annotation.*;  
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.example.user_service.DTO.UserRequestDTO;
import com.example.user_service.DTO.UserResponseDTO;
import com.example.user_service.service.UserService;

@RestController
@RequestMapping("/api/v1/users/signup")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> signup(@RequestBody UserRequestDTO requestDTO) {
        UserResponseDTO responseDTO = userService.createUser(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
}
