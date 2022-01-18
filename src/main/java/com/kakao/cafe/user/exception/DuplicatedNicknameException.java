package com.kakao.cafe.user.exception;

import com.kakao.cafe.common.exception.BadRequestException;

public class DuplicatedNicknameException extends BadRequestException {

    public DuplicatedNicknameException() {
        super("해당 닉네임이 이미 존재합니다.");
    }
}
