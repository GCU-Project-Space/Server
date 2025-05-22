package com.example.user_service;

import com.example.user_service.exception.CustomException;
import com.example.user_service.exception.UserExceptionDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

public record ApiResponse<T>(
        @JsonIgnore
        HttpStatus httpStatus,
        boolean isSuccess,
        @Nullable int code,
        @Nullable String message,
        @Nullable T data

) {

    public static <T> ApiResponse<T> ok(@Nullable final T data, @Nullable final String message) {
        return new ApiResponse<>(HttpStatus.OK, true, 1000, message, data);
    }

    public static <T> ApiResponse<T> created(@Nullable final T data, @Nullable final String message) {
        return new ApiResponse<>(HttpStatus.CREATED, true, 1001, message, data);
    }

    public static <T> ApiResponse<T> fail(final CustomException e) {
        UserExceptionDTO dto = UserExceptionDTO.of(e.getErrorCode());
        return new ApiResponse<>(e.getErrorCode().getHttpStatus(), false, dto.getCode(), dto.getMessage(), null);
    }


}

