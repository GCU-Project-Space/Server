package com.example.recruitment.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;

import java.util.List;

public record ApiResponse<T>(
        @JsonIgnore HttpStatus httpStatus,
        boolean isSuccess,
        int code,
        String message,
        T data,
        List<FieldErrorDetail> errors
) {
    public static <T> ApiResponse<T> ok(T data, String message) {
        return new ApiResponse<>(HttpStatus.OK, true, 1000, message, data, null);
    }

    public static <T> ApiResponse<T> created(T data, String message) {
        return new ApiResponse<>(HttpStatus.CREATED, true, 1001, message, data, null);
    }

    public static <T> ApiResponse<T> fail(int code, String message, HttpStatus status) {
        return new ApiResponse<>(status, false, code, message, null, null);
    }

    public static <T> ApiResponse<T> validationFail(List<FieldErrorDetail> errors) {
        return new ApiResponse<>(HttpStatus.BAD_REQUEST, false, 2001, "입력 값 유효성 검사 실패", null, errors);
    }
}

