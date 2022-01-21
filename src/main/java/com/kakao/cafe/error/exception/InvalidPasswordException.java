package com.kakao.cafe.error.exception;

import static com.kakao.cafe.Constant.MESSAGE_INVALID_PASSWORD;

public class InvalidPasswordException extends RuntimeException{
    public InvalidPasswordException() {
        super(MESSAGE_INVALID_PASSWORD);
    }
}
