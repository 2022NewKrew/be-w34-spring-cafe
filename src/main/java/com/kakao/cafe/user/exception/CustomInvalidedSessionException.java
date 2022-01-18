package com.kakao.cafe.user.exception;

public class CustomInvalidedSessionException extends RuntimeException {

    public CustomInvalidedSessionException() {
        super("[ERROR] 유효하지 않은 세션입니다.");
    }
}
