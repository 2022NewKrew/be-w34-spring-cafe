package com.kakao.cafe.exception;

public class CafeException extends RuntimeException {
    public CafeException() {
        super();
    }

    public CafeException(String message) {
        super(message);
    }
}
