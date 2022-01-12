package com.kakao.cafe.util.exception;

public class UserNotFoundException extends KakaoCafeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
