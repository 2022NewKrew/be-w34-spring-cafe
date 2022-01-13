package com.kakao.cafe.exception;

public class UserDuplicatedException extends CustomException {

    public UserDuplicatedException() {
        super(ErrorCode.USER_DUPLICATED);
    }
}
