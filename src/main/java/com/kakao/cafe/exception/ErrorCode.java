package com.kakao.cafe.exception;

public enum ErrorCode {

    USER_DUPLICATED(400, "Duplicated UserId"),
    USER_NOT_FOUND(404, "User Not Found"),
    INVALID_DTO(400, "Invalid parameter exists");

    private final int status;
    private final String message;

    ErrorCode(final int status, final String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
