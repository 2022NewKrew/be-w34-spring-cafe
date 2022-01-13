package com.kakao.cafe.error.exception;

import com.kakao.cafe.error.ErrorCode;

public class ArticleNotFoundException extends RuntimeException {

    private static final String MESSAGE_FORMAT = "Not Found Article [ID : %d]";
    private final ErrorCode errorCode;

    public ArticleNotFoundException(ErrorCode errorCode, Long id) {
        super(String.format(MESSAGE_FORMAT, id));
        this.errorCode = errorCode;
    }
}
