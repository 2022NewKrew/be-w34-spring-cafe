package com.kakao.cafe.exception;

public class NoLoginException extends RuntimeException {
    public NoLoginException(String msg) {
        super(msg);
    }
}
