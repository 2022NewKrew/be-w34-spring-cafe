package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.entity.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {
    Article save(Article user);

    Optional<Article> findOneById(Long id);

    List<Article> findAll();

    void clear();

    void update(long id, String title, String contents, String writer);

    void delete(long id);
}
