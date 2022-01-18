package com.kakao.cafe.global.error.exception;

public class NoPermissionException extends RuntimeException{
    public NoPermissionException() {
        super("해당 권한이 없습니다.");
    }

    public NoPermissionException(String message) {
        super(message);
    }

    public NoPermissionException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoPermissionException(Throwable cause) {
        super(cause);
    }
}
