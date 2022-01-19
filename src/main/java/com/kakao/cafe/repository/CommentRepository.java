package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Comment;

import java.util.List;

public interface CommentRepository {
    void save(Comment comment);

    List<Comment> findByQnaIndexAndDeleted(Integer index, Boolean isDeleted);
}
