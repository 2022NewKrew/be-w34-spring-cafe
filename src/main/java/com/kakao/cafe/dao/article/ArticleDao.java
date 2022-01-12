package com.kakao.cafe.dao.article;

import com.kakao.cafe.model.Article;

import java.util.List;

public interface ArticleDao {
    List<Article> getPartOfArticles(int startIndex, int finishIndex);

    void add(Article article);

    Article get(int id);

    int getSize();
}
