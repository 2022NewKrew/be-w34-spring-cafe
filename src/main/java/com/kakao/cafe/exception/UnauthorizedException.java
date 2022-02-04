package com.kakao.cafe.exception;

import com.kakao.cafe.error.ErrorCode;
import lombok.Getter;

@Getter
public class UnauthorizedException extends RuntimeException {
    private final ErrorCode errorCode;

    public UnauthorizedException(String msg, ErrorCode errorCode) {
        super(msg);
        this.errorCode = errorCode;
    }
}