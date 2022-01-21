package com.kakao.cafe.util.exception;

import com.kakao.cafe.util.CustomException;
import com.kakao.cafe.util.ErrorCode;

public class NonExistUserException extends CustomException {
    public NonExistUserException(ErrorCode errorCode) {
        super(errorCode);
    }
}
