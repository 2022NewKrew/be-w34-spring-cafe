package com.kakao.cafe.util;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

public enum ErrorCode {

    NOT_EXIST_USER(NOT_FOUND, "사용자 정보가 존재하지 않습니다."),
    NOT_EXIST_ARTICLE(NOT_FOUND, "요청하신 글이 존재하지 않습니다."),
    WRONG_PAGE_ACCESS(BAD_REQUEST, "잘못된 접근입니다.")
    ;

    private final HttpStatus httpStatus;
    private final String detail;

    ErrorCode(HttpStatus httpStatus, String detail) {
        this.httpStatus = httpStatus;
        this.detail = detail;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getDetail() {
        return detail;
    }
}
