package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;

import java.util.List;
import java.util.Optional;


public interface ArticleRepository {
    List<Article> selectAll();

    Optional<Article> selectByKey(Long key);

    Long insert(Article article);

    void update(Long key, Article article);

    void delete(Long key);
}
