package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository {
    Article save(Article article);

    Optional<Article> findByArticleId(Long articleId);

    List<Article> findAll();
}
