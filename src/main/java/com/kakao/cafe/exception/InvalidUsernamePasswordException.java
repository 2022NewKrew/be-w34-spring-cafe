package com.kakao.cafe.exception;

public class InvalidUsernamePasswordException extends CustomException {
    public InvalidUsernamePasswordException() {
        super(ErrorCode.INVALID_USERNAME_PASSWORD);
    }
}
