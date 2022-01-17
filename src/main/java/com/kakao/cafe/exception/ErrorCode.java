package com.kakao.cafe.exception;

public enum ErrorCode {
    USER_NOT_FOUND(404,"User not found"),

    // User registration(email, username, password, displayName)
    // 0. Common
    INVALID_FORMAT(409, "All field should not be blank"),
    // 1. email
    EMAIL_DUPLICATED(409,"Email duplicated"),
    // 2. username
    USERNAME_DUPLICATED(409,"Username duplicated"),

    // Posting(title, author, content)
    // 0. Common
    // INVALID_FORMAT(409, "All field should not be blank"),
    // 1. author
    // USER_NOT_FOUND(404,"User not found"),

    SERVER_ERROR(500,"Oops...");

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    private int status;
    private String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
