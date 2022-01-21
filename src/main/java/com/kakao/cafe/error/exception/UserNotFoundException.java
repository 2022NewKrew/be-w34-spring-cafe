package com.kakao.cafe.error.exception;

import static com.kakao.cafe.Constant.MESSAGE_USER_NOT_FOUND;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException() {
        super(MESSAGE_USER_NOT_FOUND);
    }
}
