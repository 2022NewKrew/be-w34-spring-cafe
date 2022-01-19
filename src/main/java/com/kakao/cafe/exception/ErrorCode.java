package com.kakao.cafe.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "Post not found"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found"),

    // User registration(email, username, password, displayName)
    // 0. Common
    INVALID_FORMAT(HttpStatus.UNPROCESSABLE_ENTITY, "All field should not be blank"), // 1. email
    EMAIL_DUPLICATED(HttpStatus.CONFLICT, "Email duplicated"), // 2. username
    USERNAME_DUPLICATED(HttpStatus.CONFLICT, "Username duplicated"),

    // User login(username, password)
    // 0. Common
    // INVALID_FORMAT(409, "All field should not be blank"),
    // 1. username
    // USERNAME_DUPLICATED(409,"Username duplicated"),
    // 2. password
    INVALID_USERNAME_PASSWORD(HttpStatus.UNAUTHORIZED, "Invalid username/password"),

    // Posting(title, author, content)
    // 0. Common
    // INVALID_FORMAT(409, "All field should not be blank"),
    // 1. author
    // USER_NOT_FOUND(404,"User not found"),

    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Oops...");

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
