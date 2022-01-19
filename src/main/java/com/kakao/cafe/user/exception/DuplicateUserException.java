package com.kakao.cafe.user.exception;

import com.kakao.cafe.util.CustomException;
import com.kakao.cafe.util.ErrorCode;

public class DuplicateUserException extends CustomException {
    public DuplicateUserException(ErrorCode errorCode) {
        super(errorCode);
    }
}
