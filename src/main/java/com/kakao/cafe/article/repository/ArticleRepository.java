package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.domain.Article;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository {
    Article save(Article article);

    Optional<Article> findByArticleId(Long articleId);

    List<Article> findAll();

    void update(Article article);

    void delete(Long articleId);
}
