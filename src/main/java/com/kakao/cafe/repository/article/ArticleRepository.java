package com.kakao.cafe.repository.article;

import com.kakao.cafe.domain.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {

    void save(Article article);

    Optional<Article> findById(Long id);

    List<Article> findAll();

    void update(Article article);

}
