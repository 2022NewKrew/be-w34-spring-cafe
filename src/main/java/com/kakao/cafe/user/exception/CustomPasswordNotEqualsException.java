package com.kakao.cafe.user.exception;

public class CustomPasswordNotEqualsException extends RuntimeException {

    public CustomPasswordNotEqualsException() {
        super("[ERROR] 패스워드 불일치");
    }
}
