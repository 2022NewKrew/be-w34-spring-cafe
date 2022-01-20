package com.kakao.cafe.application.user.validation;

public enum UserErrorCode {
    DUPLICATED_USER_ID("이미 존재하는 ID 입니다."),
    NON_EXISTS_USER_ID("존재하지 않는 ID 입니다."),
    PASSWORD_NOT_MATCH("패스워드가 일치하지 않습니다."),
    UNAUTHORIZED("권한이 없습니다.")
    ;

    private final String message;

    UserErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
