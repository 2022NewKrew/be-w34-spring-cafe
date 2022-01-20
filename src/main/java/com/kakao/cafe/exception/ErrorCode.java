package com.kakao.cafe.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    // 400 Bad Request
    INCORRECT_PASSWORD(HttpStatus.BAD_REQUEST, "잘못된 비밀번호입니다."),

    // 401 Unauthorized
    USER_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다"),

    // 403 Forbidden
    NOT_ALLOWED_USER(HttpStatus.FORBIDDEN, "접근할 수 없는 계정입니다."),

    // 404 Not Found
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 계정입니다."),
    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 글입니다."),

    // 409 Conflict
    DUPLICATE_USER_ID(HttpStatus.CONFLICT, "이미 존재하는 아이디입니다.");

    private final HttpStatus httpStatus;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
}
