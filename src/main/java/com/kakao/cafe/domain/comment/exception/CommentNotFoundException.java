package com.kakao.cafe.domain.comment.exception;

public class CommentNotFoundException extends IllegalArgumentException {

    public CommentNotFoundException() {
        super("해당하는 댓글을 찾을 수 없습니다.");
    }
}
