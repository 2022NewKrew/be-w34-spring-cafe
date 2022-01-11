package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.domain.Article;
import java.util.List;
import java.util.Optional;

public interface ArticleRepository {

    void save(Article article);

    Optional<Article> findById(Long id);

    List<Article> findAll();
}
