package com.kakao.cafe.infra.exception;

public class DuplicateUserException extends CustomRuntimeException {

    public DuplicateUserException(String msg) {
        super(msg);
        name = "DuplicateUserException";
        status = 400;
    }
}
