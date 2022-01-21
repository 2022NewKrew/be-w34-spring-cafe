package com.kakao.cafe.global.error.exception;

import java.util.NoSuchElementException;

public class NoSuchCommentException extends NoSuchElementException {

    public NoSuchCommentException() {
        super("해당 댓글이 없습니다.");
    }

    public NoSuchCommentException(String s) {
        super(s);
    }
}
