package com.kakao.cafe.error.exception;

import static com.kakao.cafe.Constant.MESSAGE_LOGIN_REQUIRED;

public class LoginException extends RuntimeException{
    public LoginException() {
        super(MESSAGE_LOGIN_REQUIRED);
    }
}
