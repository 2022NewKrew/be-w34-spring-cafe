package com.kakao.cafe.util.exception;

public class UserDuplicatedException extends KakaoCafeException {
    public UserDuplicatedException(String message) {
        super(message);
    }
}
