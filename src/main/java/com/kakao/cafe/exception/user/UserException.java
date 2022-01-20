package com.kakao.cafe.exception.user;

import com.kakao.cafe.exception.CafeException;

public class UserException extends CafeException {
    public UserException() {
        super();
    }

    public UserException(String message) {
        super(message);
    }
}
