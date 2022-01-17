package com.kakao.cafe.user.exception;

public enum UserAccountErrorCode {
    ID_NOT_FOUND(404, "일치하는 ID가 존재하지 않습니다"),
    EMAIL_NOT_FOUND(404, "일치하는 EMAIL이 존재하지 않습니다"),
    DUPLICATED_EMAIL(400, "중복된 EMAIL이 존재합니다");

    private final int code;
    private final String description;

    UserAccountErrorCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
