package com.kakao.cafe.post.exception;

public class QuestionPostException extends RuntimeException {

    private final QuestionPostErrorCode errorCode;

    public QuestionPostException(QuestionPostErrorCode errorCode) {
        super(errorCode.getDescription());
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode.getCode();
    }

    public String getErrorMessage() {
        return errorCode.getDescription();
    }
}
