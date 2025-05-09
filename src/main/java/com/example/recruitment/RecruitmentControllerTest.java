package com.example.recruitment;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecruitmentControllerTest {

    @GetMapping("/")
    public String hello() {
        return "서버 정상 작동 중!";
    }
}
