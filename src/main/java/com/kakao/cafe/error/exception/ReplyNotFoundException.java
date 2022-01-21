package com.kakao.cafe.error.exception;

import com.kakao.cafe.error.ErrorCode;

public class ReplyNotFoundException extends RuntimeException {

    private static final String MESSAGE_FORMAT = "Not Found Reply [ID : %d]";
    private final ErrorCode errorCode;

    public ReplyNotFoundException(ErrorCode errorCode, Long id) {
        super(String.format(MESSAGE_FORMAT, id));
        this.errorCode = errorCode;
    }
}
