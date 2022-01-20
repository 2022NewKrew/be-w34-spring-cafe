package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.persistence.Reply;

import java.util.List;
import java.util.Optional;

public interface ReplyRepository {

    void save(Reply reply, Long articleId);

    List<Reply> findByArticleId(Long articleId);
    Optional<Reply> findById(Long id);

    void deleteByArticleId(Long id);
}
