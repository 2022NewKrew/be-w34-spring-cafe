package com.kakao.cafe.repository.article;

import com.kakao.cafe.domain.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {
    Article save(Article article);

    Long delete(Long id);

    Article increaseViewCount(Article article);

    Optional<Article> findById(Long id);

    Optional<Long> findUidById(Long id);

    Optional<String> findUserNicknameById(Long userId);

    List<Article> findAll();
}
