package com.kakao.cafe.common.exception;

public class UserAuthException extends RuntimeException {
    public UserAuthException(String message) {
        super(message);
    }
}
