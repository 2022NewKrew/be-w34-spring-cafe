package com.kakao.cafe.error.exception;

import static com.kakao.cafe.Constant.MESSAGE_USER_ALREADY_EXIST;

public class UserAlreadyExistException extends RuntimeException{
    public UserAlreadyExistException() {
        super(MESSAGE_USER_ALREADY_EXIST);
    }
}
