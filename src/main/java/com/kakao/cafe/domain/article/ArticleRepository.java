package com.kakao.cafe.domain.article;

import java.util.List;

public interface ArticleRepository {
    void save(Article article);
    Article findById(int articleId);
    List<Article> findAll();
}
