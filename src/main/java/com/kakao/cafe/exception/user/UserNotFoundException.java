package com.kakao.cafe.exception.user;

import com.kakao.cafe.exception.CustomException;
import com.kakao.cafe.exception.ErrorCode;

public class UserNotFoundException extends CustomException {

    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
