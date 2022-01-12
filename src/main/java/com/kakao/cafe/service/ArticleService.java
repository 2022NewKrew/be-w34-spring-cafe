package com.kakao.cafe.service;

import com.kakao.cafe.vo.Article;

import java.util.List;

public interface ArticleService {
    void write(Article article);

    List<Article> getArticles();

    Article findByArticleId(Integer articleId);
}
