package com.kakao.cafe.article.domain;

import com.kakao.cafe.article.dto.SingleComment;
import java.util.List;
import java.util.Optional;

public interface CommentRepository {

    void save(Comment comment);

    Optional<Comment> findById(Long id);

    List<SingleComment> findAllByArticleId(Long articleId);
}
