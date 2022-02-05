package com.kakao.cafe.exception;

import com.kakao.cafe.util.ErrorCode;

public class UserAccessException extends CustomException {
    public UserAccessException(ErrorCode errorCode) {
        super(errorCode);
    }
}
