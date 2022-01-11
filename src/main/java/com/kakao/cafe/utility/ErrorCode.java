package com.kakao.cafe.utility;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INVALID_USER_ID(BAD_REQUEST, "잘못된 형식의 아이디 입니다."),
    INVALID_USER_NAME(BAD_REQUEST, "잘못된 형식의 이름 입니다."),
    INVALID_USER_PASSWORD(BAD_REQUEST, "잘못된 형식의 패스워드 입니다."),
    INVALID_USER_EMAIL(BAD_REQUEST, "잘못된 형식의 이메일 입니다."),
    DUPLICATE_USER_ID(BAD_REQUEST, "존재하는 아이디 입니다."),
    USER_NOT_FOUND(NOT_FOUND, "유저를 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String errorMessage;
}
