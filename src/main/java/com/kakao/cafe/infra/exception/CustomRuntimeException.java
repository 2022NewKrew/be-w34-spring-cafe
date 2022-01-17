package com.kakao.cafe.infra.exception;

import lombok.Getter;

@Getter
public class CustomRuntimeException extends RuntimeException {

    String name;
    int status;

    public CustomRuntimeException(String msg) {
        super(msg);
    }
}
