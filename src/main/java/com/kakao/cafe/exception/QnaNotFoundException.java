package com.kakao.cafe.exception;

public class QnaNotFoundException extends RuntimeException {
    public QnaNotFoundException(Integer index) {
        super("Qna Not Found (index: " + index + ")");
    }
}
