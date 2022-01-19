package com.kakao.cafe.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    UNAUTHORIZED_ACCESS(HttpStatus.UNAUTHORIZED, "Unauthorized access"),
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "Post not found"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found"),
    INVALID_FORMAT(HttpStatus.UNPROCESSABLE_ENTITY, "All field should not be blank"),
    EMAIL_DUPLICATED(HttpStatus.CONFLICT, "Email duplicated"),
    USERNAME_DUPLICATED(HttpStatus.CONFLICT, "Username duplicated"),
    INVALID_USERNAME_PASSWORD(HttpStatus.UNAUTHORIZED, "Invalid username/password"),
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
