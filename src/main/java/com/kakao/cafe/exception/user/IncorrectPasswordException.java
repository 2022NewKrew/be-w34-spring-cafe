package com.kakao.cafe.exception.user;

import com.kakao.cafe.exception.CustomException;
import com.kakao.cafe.exception.ErrorCode;

public class IncorrectPasswordException extends CustomException {

    public IncorrectPasswordException() {
        super(ErrorCode.INCORRECT_PASSWORD);
    }
}
