package com.kakao.cafe.dao.article;

import com.kakao.cafe.model.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleDao {
    List<Article> getArticles(int pageNumber, int articlesPerPage);

    void addArticle(String title, String writer, String contents);

    Optional<Article> findArticleById(int id);

    int getSize();
}
