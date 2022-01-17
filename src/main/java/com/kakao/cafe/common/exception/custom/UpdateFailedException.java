package com.kakao.cafe.common.exception.custom;

import com.kakao.cafe.common.exception.data.ErrorCode;

public class UpdateFailedException extends RuntimeException implements CustomException {

    private final ErrorCode errorCode;

    public UpdateFailedException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public UpdateFailedException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    @Override
    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
