package com.kakao.cafe.exception.user;

public class IncorrectPasswordException extends LoginFailedException {
    public IncorrectPasswordException(String message) {
        super(message);
    }
}
