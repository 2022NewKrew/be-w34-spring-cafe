package com.kakao.cafe.exception;

public class AlreadyDeletedQnaException extends RuntimeException {
    public AlreadyDeletedQnaException(Integer qnaId) {
        super("Already Deleted Qna (qna id: " + qnaId + ")");
    }
}
