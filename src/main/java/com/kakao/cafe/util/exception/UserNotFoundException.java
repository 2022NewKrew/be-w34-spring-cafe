package com.kakao.cafe.util.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String id) {
        super(String.format("User에 해당 ID가 존재하지 않습니다! : %s", id));
    }
}
