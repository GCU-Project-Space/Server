package com.example.user_service.service;

import com.example.user_service.DTO.UserLoginRequestDTO;
import com.example.user_service.DTO.UserLoginResponseDTO;
import com.example.user_service.DTO.UserRequestDTO;
import com.example.user_service.DTO.UserResponseDTO;
import com.example.user_service.entity.User;
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
            throw new IllegalArgumentException("학교 이메일(.ac.kr)만 사용할 수 있습니다.");
        }

        if(userRepository.existsByEmail(email)){
            throw new IllegalArgumentException("이미 사용중인 이메일입니다.");
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
                .inactiveAt(savedUser.getInactiveAt())
                .build();
    }

    public UserLoginResponseDTO login(UserLoginRequestDTO requestDTO){
        String nickname = requestDTO.getNickname();
        String email = requestDTO.getEmail();
        String password = requestDTO.getPassword();

        User user = userRepository.findByNickname(nickname)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 닉네임입니다."));

        if (!user.getEmail().equals(email)) {
            throw new IllegalArgumentException("이메일이 일치하지 않습니다.");
        }

        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return UserLoginResponseDTO.builder()
                .nickname(user.getNickname())
                .message("로그인 성공 !")
                .build();
    }
}