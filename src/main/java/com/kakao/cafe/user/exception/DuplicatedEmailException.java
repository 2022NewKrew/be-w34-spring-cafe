package com.kakao.cafe.user.exception;

import com.kakao.cafe.common.exception.NotFoundException;

public class DuplicatedEmailException extends NotFoundException {

    public DuplicatedEmailException() {
        super("해당 이메일이 이미 존재합니다.");
    }
}
