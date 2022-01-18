package com.kakao.cafe.global.error.exception;

import java.util.NoSuchElementException;

public class NoSuchUserException extends NoSuchElementException {
    public NoSuchUserException(String s) {
        super(s);
    }

    public NoSuchUserException() {
        super("해당 아이디의 유저가 없습니다.");
    }
}
