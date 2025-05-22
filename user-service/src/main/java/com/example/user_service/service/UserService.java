package com.example.user_service.service;

import com.example.user_service.DTO.UserLoginRequestDTO;
import com.example.user_service.DTO.UserRequestDTO;
import com.example.user_service.DTO.UserResponseDTO;
import com.example.user_service.DTO.UserUpdateRequestDTO;
import com.example.user_service.entity.User;
import com.example.user_service.exception.CustomException;
import com.example.user_service.exception.ErrorCode;
import com.example.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public UserResponseDTO createUser(UserRequestDTO requestDTO) {
        String email = requestDTO.getEmail();

        if(!email.endsWith("ac.kr")){
            throw new CustomException(ErrorCode.INVALID_EMAIL_TYPE);
        }

        if(userRepository.existsByEmail(email)){
            throw new CustomException(ErrorCode.DUPLICATE_EMAIL);
        }

        User user = User.builder()
                .nickname(requestDTO.getNickname())
                .password(requestDTO.getPassword())  // 비밀번호는 실제론 암호화 해야 함!
                .school(requestDTO.getSchool())
                .phoneNumber(requestDTO.getPhoneNumber())
                .email(requestDTO.getEmail())
                .schoolId(requestDTO.getSchoolId())
                .createdAt(LocalDateTime.now())
                .build();

        User savedUser = userRepository.save(user);

        return UserResponseDTO.builder()
                .id(savedUser.getId())
                .nickname(savedUser.getNickname())
                .school(savedUser.getSchool())
                .phoneNumber(savedUser.getPhoneNumber())
                .email(savedUser.getEmail())
                .schoolId(savedUser.getSchoolId())
                .createdAt(savedUser.getCreatedAt())
                .build();
    }

    public UserResponseDTO login(UserLoginRequestDTO requestDTO){
        String email = requestDTO.getEmail();
        String password = requestDTO.getPassword();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND_BY_EMAIL));

        if (!user.getPassword().equals(password)) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }

        return UserResponseDTO.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .school(user.getSchool())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .schoolId(user.getSchoolId())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public UserResponseDTO getUserById(Long userId){
        User user = userRepository.findById(userId)
                    .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        return UserResponseDTO.builder()
                .nickname(user.getNickname())
                .id(user.getId())
                .nickname(user.getNickname())
                .school(user.getSchool())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .schoolId(user.getSchoolId())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public UserResponseDTO updateUser(Long userId, UserUpdateRequestDTO requestDTO){
        User user = userRepository.findById(userId)
                    .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        boolean isUpdated = false;

        if(requestDTO.getNickname()!=null) {
            user.setNickname(requestDTO.getNickname());
            isUpdated = true;
        }
        if(requestDTO.getPassword()!=null) {
            user.setPassword(requestDTO.getPassword());
            isUpdated = true;
        }
        ;
        if(requestDTO.getPhoneNumber()!=null) {
            user.setPhoneNumber(requestDTO.getPhoneNumber());
            isUpdated = true;
        }

        if(isUpdated){
            userRepository.save(user);
        }
        
        return UserResponseDTO.builder()
        .id(user.getId())
        .nickname(user.getNickname())
        .school(user.getSchool())
        .phoneNumber(user.getPhoneNumber())
        .email(user.getEmail())
        .schoolId(user.getSchoolId())
        .createdAt(user.getCreatedAt())
        .build();
    }
}