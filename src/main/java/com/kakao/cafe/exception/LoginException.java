package com.kakao.cafe.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginException extends Exception {
    public LoginException() {
        super();
    }

    public LoginException(String msg) {
        super(msg);
    }
}
