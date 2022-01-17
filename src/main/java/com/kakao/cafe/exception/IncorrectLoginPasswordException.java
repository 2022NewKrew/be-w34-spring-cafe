package com.kakao.cafe.exception;

public class IncorrectLoginPasswordException extends RuntimeException {
    public IncorrectLoginPasswordException(String message) {
        super(message);
    }
}
