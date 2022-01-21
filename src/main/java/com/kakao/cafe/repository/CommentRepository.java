package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    void save(Comment comment);

    void batchUpdate(List<Comment> comments);

    List<Comment> findByQnaIdAndDeleted(Integer qnaId, Boolean isDeleted);

    Optional<Comment> findById(Integer id);


}
