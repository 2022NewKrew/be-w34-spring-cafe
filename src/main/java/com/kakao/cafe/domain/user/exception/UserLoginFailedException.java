package com.kakao.cafe.domain.user.exception;

public class UserLoginFailedException extends RuntimeException {

    public UserLoginFailedException(String message) {
        super(message);
    }

    public UserLoginFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
