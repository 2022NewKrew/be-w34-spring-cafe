package com.kakao.cafe.exception.user;

import com.kakao.cafe.exception.CustomException;
import com.kakao.cafe.exception.ErrorCode;

public class NotAllowedUserException extends CustomException {

    public NotAllowedUserException() {
        super(ErrorCode.NOT_ALLOWED_USER);
    }
}
