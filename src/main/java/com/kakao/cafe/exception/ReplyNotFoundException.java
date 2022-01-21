package com.kakao.cafe.exception;

public class ReplyNotFoundException extends RuntimeException {

    public ReplyNotFoundException(String message) {
        super(message);
    }

    public ReplyNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
