package com.example.recruitment.exception;

import com.example.recruitment.common.ApiResponse;
import com.example.recruitment.common.FieldErrorDetail;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 커스텀 예외 처리
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse<Object>> handleCustomException(CustomException e) {
        ErrorCode ec = e.getErrorCode();
        return ResponseEntity
                .status(ec.getStatus())
                .body(ApiResponse.fail(ec.getCode(), ec.getMessage(), ec.getStatus()));
    }

    // 유효성 검사 실패
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationException(MethodArgumentNotValidException e) {
        List<FieldErrorDetail> errorList = e.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> new FieldErrorDetail(error.getField(), error.getDefaultMessage()))
                .toList();

        return ResponseEntity
                .badRequest()
                .body(ApiResponse.validationFail(errorList));
    }

    // ❗ IllegalArgumentException 처리 추가
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Object>> handleIllegalArgument(IllegalArgumentException e) {
        return ResponseEntity
                .badRequest()
                .body(ApiResponse.fail(4000, e.getMessage(), HttpStatus.BAD_REQUEST));
    }

    // 그 외 모든 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGeneralException(Exception e, HttpServletRequest request) throws Exception {
        String path = request.getRequestURI();

        // Swagger 요청은 무시
        if (path != null && (path.contains("/v3/api-docs") || path.contains("/swagger"))) {
            throw e;
        }

        return ResponseEntity
                .status(ErrorCode.INTERNAL_ERROR.getStatus())
                .body(ApiResponse.fail(
                        ErrorCode.INTERNAL_ERROR.getCode(),
                        ErrorCode.INTERNAL_ERROR.getMessage(),
                        ErrorCode.INTERNAL_ERROR.getStatus()
                ));
    }
}
