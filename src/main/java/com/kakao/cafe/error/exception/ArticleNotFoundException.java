package com.kakao.cafe.error.exception;

import com.kakao.cafe.error.ErrorCode;
import lombok.Getter;

@Getter
public class ArticleNotFoundException extends RuntimeException {

    private static final String messageFormat = "Article [ID : %d] %s";
    private final ErrorCode errorCode;

    public ArticleNotFoundException(ErrorCode errorCode, Long id) {
        super(String.format(messageFormat, id, errorCode.getErrorMessage()));
        this.errorCode = errorCode;
    }
}
