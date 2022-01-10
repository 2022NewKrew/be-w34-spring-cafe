package com.kakao.cafe.user.exception;

import com.kakao.cafe.common.exception.NotFoundException;

public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException() {
        super("해당 유저가 존재하지 않습니다.");
    }
}
