package com.kakao.cafe.dao.article;

import com.kakao.cafe.model.Article;

import java.util.List;

public interface ArticleDao {
    List<Article> getArticles(int pageNumber, int ArticlesPerPage);

    void addArticle(String title, String writer, String contents);

    Article findArticleById(int id);

    int getSize();
}
