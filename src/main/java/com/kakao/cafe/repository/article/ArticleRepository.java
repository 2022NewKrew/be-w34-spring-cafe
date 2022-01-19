package com.kakao.cafe.repository.article;

import com.kakao.cafe.domain.article.Article;

import java.util.List;

public interface ArticleRepository {
    int save(Article article);
    Article findById(int articleId);
    List<Article> findAll();
}
