package com.kakao.cafe.exception;

public class QnaNotFoundException extends RuntimeException {
    public QnaNotFoundException(Integer id) {
        super("Qna Not Found (id: " + id + ")");
    }
}
