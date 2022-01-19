package com.kakao.cafe.exception;

import lombok.Getter;

@Getter
public class ArticleException extends RuntimeException {
    public final ErrorCode errorCode;

    public ArticleException(ErrorCode errorCode) {
        super(errorCode.getErrorMessage());
        this.errorCode = errorCode;
    }
}
