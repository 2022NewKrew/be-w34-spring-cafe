package com.kakao.cafe.user.exception;

import com.kakao.cafe.common.exception.NotFoundException;

public class DuplicatedNicknameException extends NotFoundException {

    public DuplicatedNicknameException() {
        super("해당 닉네임이 이미 존재합니다.");
    }
}
