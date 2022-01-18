package com.kakao.cafe.global.error.exception;

public class SpringCafeBusinessException extends RuntimeException{
    public SpringCafeBusinessException() {
    }

    public SpringCafeBusinessException(String message) {
        super(message);
    }

    public SpringCafeBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public SpringCafeBusinessException(Throwable cause) {
        super(cause);
    }
}
