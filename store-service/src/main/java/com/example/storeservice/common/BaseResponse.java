package com.example.storeservice.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseResponse<T> {
    private final boolean isSuccess;
    private final int code;
    private final String message;
    private final T data;

    public static <T> BaseResponse<T> success(int code, String message, T data) {
        return new BaseResponse<T>(true, code, message, data);
    }

    public static <T> BaseResponse<T> success(String message, T data) {
        return new BaseResponse<T>(true, 1000, message, data);
    }

    public static <T> BaseResponse<T> success(String message) {
        return new BaseResponse<T>(true, 1000, message, null);
    }

    public static <T> BaseResponse<T> fail(int code, String message) {
        return new BaseResponse<T>(false, code, message, null);
    }
}
