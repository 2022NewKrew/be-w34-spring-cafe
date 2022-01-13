package com.kakao.cafe.global.error.exception;

import java.util.NoSuchElementException;

// 존재하지 않는 article ID 로 조회 했을 시
public class NoSuchArticleException extends NoSuchElementException {
    public NoSuchArticleException() {
    }

    public NoSuchArticleException(String message) {
        super(message);
    }
}
