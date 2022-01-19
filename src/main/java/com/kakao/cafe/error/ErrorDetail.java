package com.kakao.cafe.error;

public class ErrorDetail {

    private final String message;

    public static ErrorDetail from(String message) {
        return new ErrorDetail(message);
    }

    private ErrorDetail(String message) {
        this.message = message;
    }
}
