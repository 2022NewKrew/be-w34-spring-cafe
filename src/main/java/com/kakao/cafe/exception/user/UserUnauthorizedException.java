package com.kakao.cafe.exception.user;

import com.kakao.cafe.exception.CustomException;
import com.kakao.cafe.exception.ErrorCode;

public class UserUnauthorizedException extends CustomException {

    public UserUnauthorizedException() {
        super(ErrorCode.USER_UNAUTHORIZED);
    }
}
