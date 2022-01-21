package com.kakao.cafe.web.exception;

public class AccessDeniedException extends RuntimeException {
    public final static String message = "허용되지 않은 접근입니다.";

    public AccessDeniedException() {
        super(message);
    }
}
