package com.kakao.cafe.exceptions;

public abstract class PostException extends RuntimeException {

    public PostException(String message) {
        super(message);
    }
}
