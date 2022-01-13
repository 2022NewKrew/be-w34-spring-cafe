package com.kakao.cafe.global.error.exception;

import java.util.NoSuchElementException;

public class NoSuchArticleException extends NoSuchElementException {
    public NoSuchArticleException() {
    }

    public NoSuchArticleException(String message) {
        super(message);
    }
}
