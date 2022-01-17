package com.kakao.cafe.exception;

public class InvalidFormatException extends CustomException {
    public InvalidFormatException() {
        super(ErrorCode.INVALID_FORMAT);
    }
}
