package com.kakao.cafe.exception;

public class IncorrectLoginPasswordException extends RuntimeException {
    public IncorrectLoginPasswordException() {
        super("Password is incorrect");
    }
}
