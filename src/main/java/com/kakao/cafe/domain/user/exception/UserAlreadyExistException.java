package com.kakao.cafe.domain.user.exception;

public class UserAlreadyExistException extends IllegalArgumentException {

    public UserAlreadyExistException(String email) {
        super(email + " 에 해당하는 사용자가 존재합니다");
    }
}
