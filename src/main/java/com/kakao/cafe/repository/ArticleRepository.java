package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;

import java.util.List;

public interface ArticleRepository {
    Long save(Article article);

    List<Article> getAllQuestions();

    Article findById(String id);

    void deleteById(String id);

    void deleteByWriter(String writer);
}
