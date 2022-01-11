package com.kakao.cafe.service;

public enum UserError {
    ALREADY_EXISTS("중복된 사용자 ID 입니다."),
    NOT_FOUND("입력한 사용자를 찾을 수 없습니다.");

    private final String message;

    UserError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
