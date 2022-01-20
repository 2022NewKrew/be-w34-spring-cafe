package com.kakao.cafe.user.exception;

public class CustomLoginFailException extends RuntimeException {

    public CustomLoginFailException() {
        super("[ERROR] 아이디 or 패스워드 불일치");
    }
}
