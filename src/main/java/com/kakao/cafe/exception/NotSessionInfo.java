package com.kakao.cafe.exception;

public class NotSessionInfo extends RuntimeException {
    public NotSessionInfo(String message) {
        super(message);
    }
}
