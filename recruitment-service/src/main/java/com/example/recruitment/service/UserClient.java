package com.example.recruitment.service;

import com.example.recruitment.dto.RecruitmentDetailDto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class UserClient {

    private final RestTemplate restTemplate;

    @Value("${user.server.url}")
    private String userServerUrl;


    public UserDto getUserById(Long userId) {
        String url = userServerUrl + "/api/v1/users/" + userId;
        ResponseEntity<UserDto> response = restTemplate.getForEntity(url, UserDto.class);
        return response.getBody();
    }
}
