package com.kakao.cafe.error;

public class ErrorMessage {
    private final String message;

    public static ErrorMessage from(String message) {
        return new ErrorMessage(message);
    }

    private ErrorMessage(String message) {
        this.message = message;
    }
}
