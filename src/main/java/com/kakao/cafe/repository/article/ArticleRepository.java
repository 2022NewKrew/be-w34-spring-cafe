package com.kakao.cafe.repository.article;

import com.kakao.cafe.domain.article.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {
    void insert(Article article);
    Optional<Article> selectByArticleId(Long id);
    List<Article> selectAll();
    void update(Article article);
}
