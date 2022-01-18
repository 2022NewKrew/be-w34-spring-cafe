package com.kakao.cafe.exception.user;

import com.kakao.cafe.exception.CustomException;
import com.kakao.cafe.exception.ErrorCode;

public class DuplicateUserIdException extends CustomException {

    public DuplicateUserIdException() {
        super(ErrorCode.DUPLICATE_USER_ID);
    }
}
