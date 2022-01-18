package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {
    Optional<Long> save(Article article);

    List<Article> getAllQuestions();

    Article findById(String id);

    void deleteById(String id);

    void deleteByWriter(String writer);

    void update(Article article);
}
