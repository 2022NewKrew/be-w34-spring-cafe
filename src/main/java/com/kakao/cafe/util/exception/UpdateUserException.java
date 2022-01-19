package com.kakao.cafe.util.exception;

import com.kakao.cafe.util.CustomException;
import com.kakao.cafe.util.ErrorCode;

public class UpdateUserException extends CustomException {

    public UpdateUserException(ErrorCode errorCode) {
        super(errorCode);
    }
}
