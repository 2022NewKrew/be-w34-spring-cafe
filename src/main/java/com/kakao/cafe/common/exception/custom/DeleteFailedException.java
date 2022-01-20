package com.kakao.cafe.common.exception.custom;

import com.kakao.cafe.common.exception.data.ErrorCode;

public class DeleteFailedException extends RuntimeException implements CustomException {

    private final ErrorCode errorCode;

    public DeleteFailedException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public DeleteFailedException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    @Override
    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
