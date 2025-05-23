package com.example.recruitment.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    BAD_REQUEST(2000, HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    NOT_FOUND(2007, HttpStatus.NOT_FOUND, "리소스를 찾을 수 없습니다."),
    FORBIDDEN(2006, HttpStatus.FORBIDDEN, "권한이 없습니다."),
    INTERNAL_ERROR(3000, HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류입니다.");

    private final int code;
    private final HttpStatus status;
    private final String message;
}
