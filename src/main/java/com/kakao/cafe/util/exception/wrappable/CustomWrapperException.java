package com.kakao.cafe.util.exception.wrappable;

import com.kakao.cafe.util.exception.CustomException;

public class CustomWrapperException extends CustomException {
    private Exception exception;

    public CustomWrapperException(Exception exception, String message) {
        super(message);
        this.exception = exception;
    }

    public String getDetail() {
        return printStackTrace(exception);
    }
}
