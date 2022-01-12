package com.kakao.cafe.error.exception;

import com.kakao.cafe.error.ErrorCode;
import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {

    private static final String messageFormat = "User [id : %s] %s";
    private final ErrorCode errorCode;

    public UserNotFoundException(ErrorCode errorCode, String userId) {
        super(String.format(messageFormat, userId, errorCode.getErrorMessage()));
        this.errorCode = errorCode;
    }
}
