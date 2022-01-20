package com.kakao.cafe.common.exception;

public class AuthException extends BusinessException{
    public AuthException(ErrorCode errorCode) {
        super(errorCode);
    }
}
