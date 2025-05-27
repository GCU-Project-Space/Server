package com.example.storeservice.response;

import com.example.storeservice.exception.ErrorCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "data"})
public class BaseResponse<T> {

    @JsonProperty("isSuccess")
    private boolean success;

    private int code;
    private String message;
    private T data;
    

    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<T>(true, 200, "성공", data);
    }

    public static <T> BaseResponse<T> fail(ErrorCode errorCode) {
        return new BaseResponse<>(false, errorCode.getCode(), errorCode.getMessage(), null);
    }

    public static <T> BaseResponse<T> fail(ErrorCode errorCode, String message) {
        return new BaseResponse<>(false, errorCode.getCode(), message, null);
    }
}
