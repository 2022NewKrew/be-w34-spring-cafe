package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.model.Comment;

public interface CommentRepository {

    void save(Comment comment);
}
