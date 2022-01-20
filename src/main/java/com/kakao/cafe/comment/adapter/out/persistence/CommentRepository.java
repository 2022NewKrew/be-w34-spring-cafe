package com.kakao.cafe.comment.adapter.out.persistence;

import com.kakao.cafe.comment.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {

    Comment create(Comment comment);

    List<Comment> findByQuestionPostId(Long QuestionPostId);

    Optional<Comment> findById(Long id);

    void delete(Long commentId);
}
