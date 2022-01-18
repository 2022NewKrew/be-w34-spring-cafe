package com.kakao.cafe.global.error.exception;

// 로그인 관련 Exception: ExceptionHandler 에서 login_failed.html view를 반환하도록 함
public class LoginException extends SpringCafeBusinessException{
    public LoginException() {}

    public LoginException(String message) {
        super(message);
    }

    public LoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginException(Throwable cause) {
        super(cause);
    }
}
