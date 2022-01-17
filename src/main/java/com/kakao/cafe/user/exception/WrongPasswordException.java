package com.kakao.cafe.user.exception;

import com.kakao.cafe.common.exception.NotFoundException;

public class WrongPasswordException extends NotFoundException {

    public WrongPasswordException() {
        super("비밀번호가 일치하지 않습니다.");
    }
}
