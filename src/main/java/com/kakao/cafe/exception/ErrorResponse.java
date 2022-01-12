package com.kakao.cafe.exception;

public class ErrorResponse {

    private final int status;
    private final String code;
    private final String message;

    public ErrorResponse(ErrorCode errorCode) {
        this.status = errorCode.getStatus();
        this.code = errorCode.getErrorCode();
        this.message = errorCode.getMessage();
    }

    public int getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
