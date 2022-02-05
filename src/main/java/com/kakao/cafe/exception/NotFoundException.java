package com.kakao.cafe.exception;

import com.kakao.cafe.util.ErrorCode;

public class NotFoundException extends CustomException {
    public NotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
