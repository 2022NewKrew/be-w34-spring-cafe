package com.kakao.cafe.domain.article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {
    Optional<Article> findById(Long id);
    List<Article> findAll();
    Long save(Article article);
    Long delete(Article article);
    Long deleteAll();
}
