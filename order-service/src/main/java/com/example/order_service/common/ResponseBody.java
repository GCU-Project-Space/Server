package com.example.order_service.common;

import io.micrometer.common.lang.Nullable;

public record ResponseBody<T>(
        boolean isSuccess,
        int code,
        @Nullable String message,
        @Nullable T data

) {

    public static <T> ResponseBody<T> ok(@Nullable final T data, @Nullable final String message) {
        return new ResponseBody<>(true, 1000, message, data);
    }

    public static <T> ResponseBody<T> created(@Nullable final T data, @Nullable final String message) {
        return new ResponseBody<>(true, 1001, message, data);
    }

    public static <T> ResponseBody<T> updated(@Nullable final T data, @Nullable final String message) {
        return new ResponseBody<>(true, 1003, message, data);
    }

    public static <T> ResponseBody<T> deleted(@Nullable final String message) {
        return new ResponseBody<>(true, 1003, message, null);
    }

    public static <T> ResponseBody<T> fail(final CustomException e) {
        ExceptionDto dto = ExceptionDto.of(e.getErrorCode());
        return new ResponseBody<>(false, dto.getCode(), dto.getMessage(), null);
    }


}
