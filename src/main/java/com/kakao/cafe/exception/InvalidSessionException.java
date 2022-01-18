package com.kakao.cafe.exception;

public class InvalidSessionException extends RuntimeException{

    public InvalidSessionException(String message) {
        super(message);
    }
}
