package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Comment;
import com.kakao.cafe.dto.CommentDTO;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Long insert(Long articleKey, Long authorKey, Comment commentDTO);
    void update(Long key, String content);
    void delete(Long key);
    void deleteByArticleKey(Long key);
    List<Comment> selectByArticleKey(Long key);
    Optional<Comment> selectByKey(Long key);
}
