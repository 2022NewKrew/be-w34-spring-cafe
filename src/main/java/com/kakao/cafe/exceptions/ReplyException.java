package com.kakao.cafe.exceptions;

public abstract class ReplyException extends RuntimeException {

    public ReplyException(String message) {
        super(message);
    }
}
