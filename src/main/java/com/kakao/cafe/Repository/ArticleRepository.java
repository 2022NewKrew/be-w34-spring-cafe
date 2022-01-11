package com.kakao.cafe.Repository;

import com.kakao.cafe.domain.Article;

import java.util.List;

public interface ArticleRepository {
    void save(Article article);

    List<Article> getAllQuestions();

    Article findById(String id);
}
