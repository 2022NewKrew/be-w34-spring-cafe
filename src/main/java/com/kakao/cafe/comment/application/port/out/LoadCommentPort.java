package com.kakao.cafe.comment.application.port.out;

import com.kakao.cafe.comment.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface LoadCommentPort {

    List<Comment> findByQuestionId(Long questionPostId);

    Optional<Comment> findById(Long id);
}
