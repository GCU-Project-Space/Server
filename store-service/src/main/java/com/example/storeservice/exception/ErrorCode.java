package com.example.storeservice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // 공통
    INTERNAL_SERVER_ERROR(false, 3000, "요청 처리 중 서버 내부 오류가 발생했습니다."),
    INVALID_REQUEST(false, 2010, "잘못된 요청입니다."),

    // 가게 관련
    STORE_NOT_FOUND(false, 2007, "해당 가게를 찾을 수 없습니다."),
    DUPLICATE_NAME(false, 2006, "중복된 가게 이름입니다."),
    DUPLICATE_PHONE(false, 2008, "중복된 전화번호입니다."),
    DUPLICATE_LOCATION(false, 2009, "중복된 위치입니다."),

    // 메뉴 관련
    DUPLICATE_MENU(false, 2011, "이미 등록된 메뉴입니다."),
    MENU_NOT_FOUND(false, 2012, "해당 메뉴를 찾을 수 없습니다."),
    INVALID_MENU_ID(false, 2013, "유효하지 않은 메뉴 ID입니다."),
    MENU_NOT_EXIST(false, 2016, "해당 가게에 등록된 메뉴가 없습니다."),

    // 할인 관련
    DISCOUNT_NOT_FOUND(false, 2014, "해당 할인 정보를 찾을 수 없습니다."),
    DUPLICATE_DISCOUNT(false, 2015, "이미 등록된 할인입니다.");

    private final boolean isSuccess;
    private final int code;
    private final String message;
}
