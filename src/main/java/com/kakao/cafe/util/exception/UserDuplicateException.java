package com.kakao.cafe.util.exception;

public class UserDuplicateException extends RuntimeException {
    public UserDuplicateException(String id) {
        super(String.format("해당 ID의 User가 이미 존재합니다! : %s", id));
    }
}
