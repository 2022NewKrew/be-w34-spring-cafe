package com.kakao.cafe.infra.exception;

import lombok.Getter;

@Getter
public class CustomRuntimeException extends RuntimeException {

    String name;
    ErrorCode errorCode;

    public CustomRuntimeException(String msg) {
        super(msg);
    }
}
