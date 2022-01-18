package com.kakao.cafe.global.error.exception;

import java.util.NoSuchElementException;

// 존재하지 않는 article ID 로 조회 했을 시
public class NoSuchArticleException extends NoSuchElementException {
    public NoSuchArticleException() {
        super("해당 게시글이 존재하지 않습니다.");
    }

    public NoSuchArticleException(String message) {
        super(message);
    }
}
