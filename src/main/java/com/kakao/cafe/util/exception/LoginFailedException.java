package com.kakao.cafe.util.exception;

import com.kakao.cafe.util.CustomException;
import com.kakao.cafe.util.ErrorCode;

public class LoginFailedException extends CustomException {
    public LoginFailedException(ErrorCode errorCode) {
        super(errorCode);
    }
}
