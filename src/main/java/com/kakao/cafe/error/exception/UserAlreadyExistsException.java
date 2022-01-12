package com.kakao.cafe.error.exception;

import com.kakao.cafe.error.ErrorCode;
import lombok.Getter;

@Getter
public class UserAlreadyExistsException extends RuntimeException {

    private static final String messageFormat = "User [AccountID : %s] %s";
    private final ErrorCode errorCode;

    public UserAlreadyExistsException(ErrorCode errorCode, String userId) {
        super(String.format(messageFormat, userId, errorCode.getErrorMessage()));
        this.errorCode = errorCode;
    }
}
