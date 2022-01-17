package com.kakao.cafe.exception;

public class NotEnoughFieldException extends RuntimeException {

    public NotEnoughFieldException() {
        super();
    }

    public NotEnoughFieldException(String message) {
        super(message);
    }
}
