package com.kakao.cafe.user.exception;

public class CustomDuplicateUserException extends RuntimeException {

    public CustomDuplicateUserException(Throwable cause) {
        super("[ERROR] 이미 존재하는 아이디입니다.", cause);
    }
}
