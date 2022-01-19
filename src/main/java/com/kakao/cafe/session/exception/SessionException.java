package com.kakao.cafe.session.exception;

public abstract class SessionException extends RuntimeException {
    protected SessionException(String message) {
        super(message);
    }
}
