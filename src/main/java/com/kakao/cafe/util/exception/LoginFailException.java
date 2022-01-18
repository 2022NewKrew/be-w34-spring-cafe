package com.kakao.cafe.util.exception;

public class LoginFailException extends CustomException {
    public LoginFailException(Exception exception, String id) {
        super(exception, String.format("해당 id로 로그인할 수 없습니다!", id));
    }
}
