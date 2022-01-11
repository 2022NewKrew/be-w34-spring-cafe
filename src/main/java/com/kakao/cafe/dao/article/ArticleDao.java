package com.kakao.cafe.dao.article;

import com.kakao.cafe.model.Article;

import java.util.List;

public interface ArticleDao {
    List<Article> getPartOfArticles(int startIndex, int finishIndex);

    void add(Article article);

    Article findArticleById(int id);

    int getSize();
}
