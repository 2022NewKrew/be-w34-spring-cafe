package com.kakao.cafe.exception;

public class UsernameDuplicatedException extends CustomException {
    public UsernameDuplicatedException() {
        super(ErrorCode.USERNAME_DUPLICATED);
    }
}
