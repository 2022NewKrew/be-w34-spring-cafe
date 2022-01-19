package com.kakao.cafe.exception;

public class NoModifyPermissionException extends RuntimeException {
    public NoModifyPermissionException(String message) {
        super(message);
    }
}
