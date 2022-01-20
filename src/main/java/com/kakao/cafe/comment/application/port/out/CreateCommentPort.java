package com.kakao.cafe.comment.application.port.out;

import com.kakao.cafe.comment.domain.Comment;

public interface CreateCommentPort {

    Comment create(Comment comment);
}
