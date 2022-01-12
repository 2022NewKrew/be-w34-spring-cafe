package com.kakao.cafe.repository;

import com.kakao.cafe.domain.article.Article;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ArticleRepository {

    void save(Article article);

    List<Article> findAll();

    Optional<Article> findArticleById(UUID id);
}
