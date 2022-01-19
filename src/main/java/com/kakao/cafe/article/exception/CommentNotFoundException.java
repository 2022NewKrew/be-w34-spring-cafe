package com.kakao.cafe.article.exception;

import com.kakao.cafe.common.exception.NotFoundException;

public class CommentNotFoundException extends NotFoundException {

    public CommentNotFoundException() {
        super("해당 댓글이 존재하지 않습니다.");
    }
}
