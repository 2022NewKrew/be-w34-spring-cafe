package com.kakao.cafe.exception;

public class IllegalUserInputException extends RuntimeException {
    public IllegalUserInputException() {
        super();
    }

    public IllegalUserInputException(String message) {
        super(message);
    }
}