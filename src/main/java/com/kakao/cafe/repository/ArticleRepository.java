package com.kakao.cafe.repository;

import com.kakao.cafe.model.Article;
import java.util.List;
import java.util.Optional;

public interface ArticleRepository {

    void add(Article article);

    List<Article> findAllArticles();

    Optional<Article> findArticleById(Long id);
}
