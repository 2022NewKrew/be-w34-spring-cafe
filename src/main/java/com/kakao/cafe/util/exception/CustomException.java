package com.kakao.cafe.util.exception;

public abstract class CustomException extends RuntimeException implements StackTracePrintable {
    public CustomException(String message) {
        super(message);
    }

    abstract public String getDetail();
}
