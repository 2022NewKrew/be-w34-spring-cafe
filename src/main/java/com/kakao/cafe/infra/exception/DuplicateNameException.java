package com.kakao.cafe.infra.exception;

public class DuplicateNameException extends CustomRuntimeException {

    public DuplicateNameException(String msg) {
        super(msg);
        name = "DuplicateException";
    }
}
