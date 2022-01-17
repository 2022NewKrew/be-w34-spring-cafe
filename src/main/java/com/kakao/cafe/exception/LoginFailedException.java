package com.kakao.cafe.exception;

public class LoginFailedException extends Exception{
    public LoginFailedException(String message) {
        super(message);
    }
}
