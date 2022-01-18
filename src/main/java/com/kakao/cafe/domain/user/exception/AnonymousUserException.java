package com.kakao.cafe.domain.user.exception;

public class AnonymousUserException extends RuntimeException {
    public AnonymousUserException(String message) {
        super(message);
    }

    public AnonymousUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
