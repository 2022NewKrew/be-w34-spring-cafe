package com.kakao.cafe.comment.domain;

import org.springframework.stereotype.Repository;

import java.util.List;

public interface CommentRepository {
    List<Comment> findAllByArticleId(int articleId);
}
