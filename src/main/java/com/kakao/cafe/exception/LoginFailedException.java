package com.kakao.cafe.exception;

import com.kakao.cafe.util.ErrorCode;

public class LoginFailedException extends CustomException {
    public LoginFailedException(ErrorCode errorCode) {
        super(errorCode);
    }
}
