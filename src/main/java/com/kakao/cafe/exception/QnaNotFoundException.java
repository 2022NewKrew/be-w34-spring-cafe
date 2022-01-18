package com.kakao.cafe.exception;

public class QnaNotFoundException extends RuntimeException {
    private final String message;

    public QnaNotFoundException(Integer index) {
        this.message = "Qna Not Found (index: " + index + ")";
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
