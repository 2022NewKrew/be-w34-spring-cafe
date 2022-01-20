package com.kakao.cafe.repository;

import com.kakao.cafe.domain.article.Comment;
import com.kakao.cafe.web.dto.CommentCreateRequestDto;

import java.util.List;

public interface CommentRepository {
    void create(CommentCreateRequestDto requestDto);
    List<Comment> findByArticleIdNotDeleted(Long articleId);
    void shiftIsDeleted(Long id);
}
