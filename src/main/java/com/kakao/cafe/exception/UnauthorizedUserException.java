package com.kakao.cafe.exception;

public class UnauthorizedUserException extends CustomException {

    public UnauthorizedUserException() {
        super(ErrorCode.UNAUTHORIZED_USER);
    }
}
