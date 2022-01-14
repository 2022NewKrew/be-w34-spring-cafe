package com.kakao.cafe.exception.custom;

import com.kakao.cafe.exception.ErrorCode;

public class DuplicateException extends RuntimeException {
    private ErrorCode errorCode;

    public DuplicateException() {
    }

    public DuplicateException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public DuplicateException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

}
