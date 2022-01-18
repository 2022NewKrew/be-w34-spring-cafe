package com.kakao.cafe.infra.exception;

public class NoSuchDataException extends CustomRuntimeException {

    public NoSuchDataException(String msg) {
        super(msg);
        name = "NoSuchDataException";
        errorCode = ErrorCode.BAD_REQUEST;
    }
}
