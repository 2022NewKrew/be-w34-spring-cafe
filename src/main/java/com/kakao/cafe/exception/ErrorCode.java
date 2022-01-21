package com.kakao.cafe.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found"),
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "Post not found"),
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "Comment not found"),
    INVALID_FORMAT(HttpStatus.UNPROCESSABLE_ENTITY, "All field should not be blank."),
    INVALID_USERNAME_PASSWORD(HttpStatus.UNAUTHORIZED, "Invalid username/password"),
    USERNAME_DUPLICATED(HttpStatus.CONFLICT, "Username duplicated"),
    EMAIL_DUPLICATED(HttpStatus.CONFLICT, "Email duplicated"),
    UNAUTHORIZED_ACCESS(HttpStatus.UNAUTHORIZED, "Unauthorized access"),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Oops! An unexpected error seems to have occurred.");

    private final HttpStatus httpStatus;
    private final String message;
}
