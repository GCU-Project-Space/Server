package com.example.user_service.service;

// import com.example.user_service.DTO.Request.StoreRequestDTO;
import com.example.user_service.DTO.Request.UserLoginRequestDTO;
import com.example.user_service.DTO.Request.UserRequestDTO;
import com.example.user_service.DTO.Request.UserUpdateRequestDTO;
// import com.example.user_service.DTO.Resposne.StoreResponseDTO;
import com.example.user_service.DTO.Resposne.UserResponseDTO;
import com.example.user_service.entity.User;
import com.example.user_service.exception.CustomException;
import com.example.user_service.exception.ErrorCode;
import com.example.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
// import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
// import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    // @Autowired
    // private RestTemplate restTemplate;


    public UserResponseDTO createUser(UserRequestDTO requestDTO) {
        String email = requestDTO.getEmail();
        String userType = requestDTO.getUserType();

        if (userRepository.existsByEmail(email)) {
            throw new CustomException(ErrorCode.DUPLICATE_EMAIL);
        }

        if ("USER".equals(userType) && !email.endsWith("ac.kr")) {
            throw new CustomException(ErrorCode.INVALID_EMAIL_TYPE);
        }


        User user = User.builder()
            .nickname(requestDTO.getNickname())
            .password(requestDTO.getPassword())  // 실제 서비스에서는 암호화 필요!
            .school(requestDTO.getSchool())
            .phoneNumber(requestDTO.getPhoneNumber())
            .email(email)
            .schoolId(requestDTO.getSchoolId())
            .createdAt(LocalDateTime.now())
            .userType(
                "USER".equals(userType) ? User.UserType.USER : User.UserType.OWNER
            )
            .storeId(requestDTO.getStoreId())
            .build();

        User savedUser = userRepository.save(user);

        // if("OWNER".equals(user.getUserType().toString())){
        //     StoreRequestDTO storeRequestDTO = new StoreRequestDTO();
        //     ResponseEntity<StoreResponseDTO> storeResponseDTO = restTemplate.postForEntity("http://54.66.149.225:8103/api/v1/stores", storeRequestDTO, StoreResponseDTO.class);
        //     Long storeId = storeResponseDTO.getBody().getStoreId();
        //     savedUser.setStoreId(storeId);
        // }

        userRepository.save(savedUser);

        return UserResponseDTO.builder()
                .id(savedUser.getId())
                .nickname(savedUser.getNickname())
                .school(savedUser.getSchool())
                .phoneNumber(savedUser.getPhoneNumber())
                .email(savedUser.getEmail())
                .schoolId(savedUser.getSchoolId())
                .createdAt(savedUser.getCreatedAt())
                .userType(savedUser.getUserType().toString())
                .storeId(savedUser.getStoreId())
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
                .userType(user.getUserType().toString())
                .storeId(user.getStoreId())
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
                .userType(user.getUserType().toString())
                .storeId(user.getStoreId())
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
        .userType(user.getUserType().toString())
        .storeId(user.getStoreId())
        .build();
    }
}