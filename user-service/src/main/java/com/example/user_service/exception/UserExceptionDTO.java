package com.example.user_service.exception;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
public class UserExceptionDTO {

    @NotNull
    private final Integer code;

    @NotNull
    private final String message;

    public UserExceptionDTO(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public static UserExceptionDTO of(ErrorCode errorCode) {
        return new UserExceptionDTO(errorCode);
    }
}
