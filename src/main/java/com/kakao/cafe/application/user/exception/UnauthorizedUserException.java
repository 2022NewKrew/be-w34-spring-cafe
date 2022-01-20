package com.kakao.cafe.application.user.exception;

import com.kakao.cafe.application.user.validation.UserErrorCode;

public class UnauthorizedUserException extends RuntimeException {
    public UnauthorizedUserException() {
        super(UserErrorCode.UNAUTHORIZED.getMessage());
    }

    public UnauthorizedUserException(String message) {
        super(message);
    }
}
