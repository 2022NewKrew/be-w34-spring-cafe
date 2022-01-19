package com.kakao.cafe.exception;

public class LoginRequiredException extends RuntimeException {

    public LoginRequiredException(String message) {
        super(message);
    }
}
