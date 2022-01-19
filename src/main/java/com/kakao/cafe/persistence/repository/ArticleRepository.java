package com.kakao.cafe.persistence.repository;

import com.kakao.cafe.persistence.model.Article;
import java.util.List;
import java.util.Optional;

public interface ArticleRepository {

    void save(Article article);

    void update(Long id, String title, String body);

    List<Article> findAllArticles();

    Optional<Article> findArticleById(Long id);
}
