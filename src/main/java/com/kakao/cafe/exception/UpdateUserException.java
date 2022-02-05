package com.kakao.cafe.exception;

import com.kakao.cafe.util.ErrorCode;

public class UpdateUserException extends CustomException {

    public UpdateUserException(ErrorCode errorCode) {
        super(errorCode);
    }
}
