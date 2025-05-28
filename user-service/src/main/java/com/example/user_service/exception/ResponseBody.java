package com.example.user_service.exception;

import io.micrometer.common.lang.Nullable;

public record ResponseBody<T>(
        boolean isSuccess,
        int code,
        @Nullable String message,
        @Nullable T data

) {

    public static <T> ResponseBody<T> success(@Nullable final T data, final int code,  @Nullable final String message) {
        return new ResponseBody<>(true, code, message, data);
    }

    public static <T> ResponseBody<T> fail(@Nullable final T data, final int code,  @Nullable final String message) {
        return new ResponseBody<>(false, code, message, data);
    }

    public static <T> ResponseBody<T> created(@Nullable final T data, @Nullable final String message) {
        return new ResponseBody<>(true, 1001, message, data);
    }

    public static <T> ResponseBody<T> ok(@Nullable final T data, @Nullable final String message) {
        return new ResponseBody<>(true, 1000, message, data);
    }

    public static <T> ResponseBody<T> updated(@Nullable final String message) {
        return new ResponseBody<>(true, 1003, message, null);
    }

    public static <T> ResponseBody<T> deleted(@Nullable final String message) {
        return new ResponseBody<>(true, 1003, message, null);
    }

}