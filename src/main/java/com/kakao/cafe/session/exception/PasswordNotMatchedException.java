package com.kakao.cafe.session.exception;

public class PasswordNotMatchedException extends SessionException {
    public PasswordNotMatchedException(String message) {
        super(message);
    }
}
