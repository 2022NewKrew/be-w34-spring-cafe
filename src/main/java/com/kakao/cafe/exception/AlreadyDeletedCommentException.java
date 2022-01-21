package com.kakao.cafe.exception;

public class AlreadyDeletedCommentException extends RuntimeException {
    public AlreadyDeletedCommentException(Integer commentId) {
        super("Already Deleted Comment (commnet id: " + commentId + ")");
    }
}
