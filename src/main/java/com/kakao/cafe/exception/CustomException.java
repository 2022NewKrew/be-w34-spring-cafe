package com.kakao.cafe.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {

    private ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public HttpStatus getResponseStatus() {
        return errorCode.getHttpStatus();
    }
}
