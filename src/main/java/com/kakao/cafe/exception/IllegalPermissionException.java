package com.kakao.cafe.exception;

public class IllegalPermissionException extends RuntimeException{
    public IllegalPermissionException() {
        super();
    }

    public IllegalPermissionException(String msg) {
        super(msg);
    }
}
