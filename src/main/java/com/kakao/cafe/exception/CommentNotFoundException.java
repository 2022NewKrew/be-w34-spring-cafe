package com.kakao.cafe.exception;

public class CommentNotFoundException extends RuntimeException {
    public CommentNotFoundException(Integer id) {
        super("Comment Not Found (comment id: " + id + ")");
    }
}
