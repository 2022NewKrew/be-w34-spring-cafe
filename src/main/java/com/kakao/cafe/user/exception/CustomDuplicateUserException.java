package com.kakao.cafe.user.exception;

import org.springframework.http.HttpStatus;

public class CustomDuplicateUserException extends RuntimeException {

    public static final HttpStatus CODE = HttpStatus.CONFLICT;

    public CustomDuplicateUserException(Throwable cause) {
        super("[ERROR] 이미 존재하는 아이디입니다.", cause);
    }
}
