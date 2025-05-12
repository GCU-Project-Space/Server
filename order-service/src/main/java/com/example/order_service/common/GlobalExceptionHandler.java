package com.example.order_service.common;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ResponseBody<Void>> handleCustomException(CustomException e) {
        // 중요: 실제 운영 환경에서는 여기서 반드시 심각한 오류 로그(스택 트레이스 포함)를 남겨야 함

        return new ResponseEntity<>(ResponseBody.fail(null, e.getCode(), e.getMessage()), e.getHttpStatus());
    
    }

    // 위에서 처리되지 않은 모든 예외 처리 (최후의 보루)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseBody<Void>> handleGlobalException(Exception e) {
        // 중요: 실제 운영 환경에서는 여기서 반드시 심각한 오류 로그(스택 트레이스 포함)를 남겨야 함

        return new ResponseEntity<>(ResponseBody.fail(null, 3000, "서버 내부 오류가 발생했습니다. 관리자에게 문의하세요."), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
