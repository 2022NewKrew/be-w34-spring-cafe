package com.kakao.cafe.exception;

public class LoginUserNotFoundException extends RuntimeException {
    public LoginUserNotFoundException(String userId) {
        super(userId + "와 일치하는 회원 정보가 없습니다");
    }
}
