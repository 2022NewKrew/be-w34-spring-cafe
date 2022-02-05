package com.kakao.cafe.exception;

import com.kakao.cafe.util.ErrorCode;

public class NonExistUserException extends CustomException {
    public NonExistUserException(ErrorCode errorCode) {
        super(errorCode);
    }
}
