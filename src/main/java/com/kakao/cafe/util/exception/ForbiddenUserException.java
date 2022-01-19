package com.kakao.cafe.util.exception;

import com.kakao.cafe.util.CustomException;
import com.kakao.cafe.util.ErrorCode;

public class ForbiddenUserException extends CustomException {
    public ForbiddenUserException(ErrorCode errorCode) {
        super(errorCode);
    }
}
