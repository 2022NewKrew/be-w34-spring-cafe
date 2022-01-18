package com.kakao.cafe.infra.exception;

public class ForbiddenException extends CustomRuntimeException {

    public ForbiddenException(String msg) {
        super(msg);
        name = "ForbiddenException";
        errorCode = ErrorCode.FORBIDDEN;
    }
}
