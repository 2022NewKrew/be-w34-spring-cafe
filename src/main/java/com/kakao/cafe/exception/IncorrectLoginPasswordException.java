package com.kakao.cafe.exception;

public class IncorrectLoginPasswordException extends RuntimeException {
    private final String message;

    public IncorrectLoginPasswordException() {
        this.message = "Password is incorrect";
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
