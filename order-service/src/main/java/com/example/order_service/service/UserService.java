package com.example.order_service.service;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="user-service", url="${http://54.66.149.225:8104/api/v1/users}")
public interface UserService {
    
}
