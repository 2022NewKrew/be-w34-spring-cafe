package com.kakao.cafe.global.error.exception;

// 최상위 비즈니스 Exception: Exception 계층화? 할 수 있도록
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
