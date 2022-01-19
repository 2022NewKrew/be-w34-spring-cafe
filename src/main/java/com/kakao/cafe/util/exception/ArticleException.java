package com.kakao.cafe.util.exception;

import com.kakao.cafe.util.ErrorCode;
import lombok.Getter;

@Getter
public class ArticleException extends RuntimeException {
    public final ErrorCode errorCode;

    public ArticleException(ErrorCode errorCode) {
        super(errorCode.getErrorMessage());
        this.errorCode = errorCode;
    }
}
