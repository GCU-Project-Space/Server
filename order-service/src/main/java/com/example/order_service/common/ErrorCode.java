package com.example.order_service.common;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // Test Error (기존 코드 유지 또는 필요시 수정/제거)
    TEST_ERROR(100, HttpStatus.BAD_REQUEST, "테스트 에러입니다."),

    // === 성공 (Success: 1xxx) ===
    // 주로 HTTP 2xx 와 함께 사용
    OK(1000, HttpStatus.OK, "요청 성공"),
    CREATED(1001, HttpStatus.CREATED, "리소스 생성 성공"),
    ACCEPTED(1002, HttpStatus.ACCEPTED, "요청이 접수되었으나 처리가 완료되지 않음"),
    NO_CONTENT(1003, HttpStatus.NO_CONTENT, "성공했으나 반환할 컨텐츠 없음"),

    // === 클라이언트 오류 (Client Errors: 2xxx) ===
    // 주로 HTTP 4xx 와 함께 사용
    BAD_REQUEST(2000, HttpStatus.BAD_REQUEST, "잘못된 요청 (일반적인 클라이언트 오류)"), // 기존 BAD_REQUEST(400,...) 대체
    VALIDATION_ERROR(2001, HttpStatus.BAD_REQUEST, "입력 값 유효성 검사 실패"),
    INVALID_TYPE(2002, HttpStatus.BAD_REQUEST, "요청 값의 타입이 잘못됨"),
    INVALID_PARAM(2003, HttpStatus.BAD_REQUEST, "필수 파라미터 누락 또는 형식 오류"),
    UNAUTHORIZED(2004, HttpStatus.UNAUTHORIZED, "인증되지 않은 사용자 (로그인 필요)"),
    INVALID_CREDENTIALS(2005, HttpStatus.UNAUTHORIZED, "잘못된 인증 정보 (ID/PW 오류 등)"),
    FORBIDDEN(2006, HttpStatus.FORBIDDEN, "접근 권한 없음"), // 기존 Forbidden(403,...) 대체
    NOT_FOUND(2007, HttpStatus.NOT_FOUND, "요청한 리소스를 찾을 수 없음"), // 기존 NOT_FOUND_END_POINT(404,...) 대체 및 이름 변경
    METHOD_NOT_ALLOWED(2008, HttpStatus.METHOD_NOT_ALLOWED, "허용되지 않은 HTTP 메소드 요청"),
    CONFLICT(2009, HttpStatus.CONFLICT, "리소스 상태 충돌 (예: 중복된 값 입력 시도)"),
    DUPLICATE_RESOURCE(2010, HttpStatus.CONFLICT, "중복된 리소스"),
    TOO_MANY_REQUESTS(2011, HttpStatus.TOO_MANY_REQUESTS, "요청 횟수 제한 초과"),

    // === 서버 오류 (Server Errors: 3xxx) ===
    // 주로 HTTP 5xx 와 함께 사용
    INTERNAL_SERVER_ERROR(3000, HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류 (일반적인 서버 오류)"), // 기존 INTERNAL_SERVER_ERROR(500,...) 대체
    DB_ERROR(3001, HttpStatus.INTERNAL_SERVER_ERROR, "데이터베이스 오류"),
    EXTERNAL_API_ERROR(3002, HttpStatus.INTERNAL_SERVER_ERROR, "외부 시스템 연동 오류"),
    SERVICE_UNAVAILABLE(3003, HttpStatus.SERVICE_UNAVAILABLE, "서비스 점검 또는 과부하"),
    TIMEOUT(3004, HttpStatus.GATEWAY_TIMEOUT, "요청 처리 시간 초과"); // HTTP 504 Gateway Timeout과 유사하므로 HttpStatus.GATEWAY_TIMEOUT 사용


    private final Integer code;        // 커스텀 응답 코드
    private final HttpStatus httpStatus; // HTTP 상태 코드
    private final String message;      // 응답 메시지
}
