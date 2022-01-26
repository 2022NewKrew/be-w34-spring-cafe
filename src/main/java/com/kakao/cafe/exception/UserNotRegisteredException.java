package com.kakao.cafe.exception;

import com.kakao.cafe.error.ErrorCode;
import lombok.Getter;

@Getter
public class UserNotRegisteredException extends RuntimeException {
    private final ErrorCode errorCode;

    public UserNotRegisteredException(String msg, ErrorCode errorCode) {
        super(msg);
        this.errorCode = errorCode;
    }
}
