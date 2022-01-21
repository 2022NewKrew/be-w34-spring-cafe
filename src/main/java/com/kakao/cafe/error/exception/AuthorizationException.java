package com.kakao.cafe.error.exception;

import static com.kakao.cafe.Constant.MESSAGE_UNAUTHORIZED;

public class AuthorizationException extends RuntimeException{
    public AuthorizationException() {
        super(MESSAGE_UNAUTHORIZED);
    }
}
