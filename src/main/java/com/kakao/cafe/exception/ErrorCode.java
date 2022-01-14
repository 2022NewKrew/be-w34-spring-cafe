package com.kakao.cafe.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE(400, "C001", "Invalid Input Value"),
    INTERNAL_SERVER_ERROR(500, "C002", "Server Error"),
    INVALID_TYPE_VALUE(400, "C003", "Invalid Type Value"),

    // Account
    DUPLICATED_USER_ID(400, "A001", "유저 아이디가 중복 되었습니다."),
    NOT_FOUND_USER_ID(400, "A002", "유저 아이디를 찾을 수 없습니다."),
    ;

    private final String code;
    private final String message;
    private int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }


}
