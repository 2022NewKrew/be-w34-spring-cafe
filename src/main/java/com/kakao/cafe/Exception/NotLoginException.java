package com.kakao.cafe.Exception;

public class NotLoginException extends RuntimeException {
    public NotLoginException(String message) {
        super(message);
    }
}
