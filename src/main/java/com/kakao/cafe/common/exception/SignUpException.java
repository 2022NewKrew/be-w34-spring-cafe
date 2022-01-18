package com.kakao.cafe.common.exception;

public class SignUpException extends BusinessException {
    public SignUpException() {
        super("해당 아이디는 이미 등록되어 있습니다.");
    }
}
