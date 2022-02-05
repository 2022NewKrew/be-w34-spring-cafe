package com.kakao.cafe.exception;

import com.kakao.cafe.util.ErrorCode;

public class UpdateException extends CustomException {

    public UpdateException(ErrorCode errorCode) {
        super(errorCode);
    }
}
