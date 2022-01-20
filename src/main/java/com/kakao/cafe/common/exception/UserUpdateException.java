package com.kakao.cafe.common.exception;

public class UserUpdateException extends BusinessException {
    public UserUpdateException() {
        super("패스워드가 일치 해야합니다.");
    }
}
