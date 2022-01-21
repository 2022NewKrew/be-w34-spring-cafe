package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.domain.Article;
import java.util.List;
import java.util.Optional;

public interface ArticleRepository {

    Article save(Article article);

    List<Article> findAll();

    Optional<Article> findById(Long id);

    void updateViewNum(Long id);

    void update(Article updateArticle);

    void delete(Long id);
}
