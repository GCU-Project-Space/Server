package com.example.order_service.common;

import org.springframework.http.HttpStatus;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomException extends RuntimeException{
    private final ErrorCode errorCode;
    
    public String getMessage() {
        return errorCode.getMessage();
    }

    public HttpStatus getHttpStatus() {
        return errorCode.getHttpStatus();
    }

    public int getCode() {
        return errorCode.getCode();
    }
}
