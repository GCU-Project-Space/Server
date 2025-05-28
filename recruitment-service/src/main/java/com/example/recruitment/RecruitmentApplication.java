package com.example.recruitment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RecruitmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecruitmentApplication.class, args);
	}

}
