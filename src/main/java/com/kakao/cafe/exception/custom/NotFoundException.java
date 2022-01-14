package com.kakao.cafe.exception.custom;

import com.kakao.cafe.exception.ErrorCode;

public class NotFoundException extends RuntimeException{
    private ErrorCode errorCode;

    public NotFoundException() {
    }

    public NotFoundException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public NotFoundException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
