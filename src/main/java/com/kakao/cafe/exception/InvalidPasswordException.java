package com.kakao.cafe.exception;

import com.kakao.cafe.error.ErrorCode;
import lombok.Getter;

@Getter
public class InvalidPasswordException extends RuntimeException {
    private final ErrorCode errorCode;

    public InvalidPasswordException(String msg, ErrorCode errorCode) {
        super(msg);
        this.errorCode = errorCode;
    }
}
