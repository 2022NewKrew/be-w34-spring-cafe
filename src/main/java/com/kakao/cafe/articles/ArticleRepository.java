package com.kakao.cafe.articles;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {
    Article save(Article article);

    Optional<Article> findById(Long id);

    List<Article> findAll();

    int getSize();
}
