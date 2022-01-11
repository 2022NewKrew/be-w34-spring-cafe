package com.kakao.cafe.repository;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.ArticleId;
import java.util.List;
import java.util.Optional;

public interface ArticleRepository {

    void save(Article article);

    List<Article> findAll();

    Optional<Article> findArticleById(ArticleId id);
}
