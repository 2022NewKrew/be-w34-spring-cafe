package com.kakao.cafe.exception;

import com.kakao.cafe.util.ErrorCode;

public class DuplicateUserException extends CustomException {
    public DuplicateUserException(ErrorCode errorCode) {
        super(errorCode);
    }
}
