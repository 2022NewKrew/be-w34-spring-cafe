package com.kakao.cafe.dao.article;

import com.kakao.cafe.model.article.Article;
import com.kakao.cafe.model.article.Contents;
import com.kakao.cafe.model.article.Title;
import com.kakao.cafe.model.article.Writer;

import java.util.List;
import java.util.Optional;

public interface ArticleDao {
    List<Article> getArticles(int pageNumber, int articlesPerPage);

    void addArticle(Title title, Writer writer, Contents contents);

    Optional<Article> findArticleById(int id);

    int getSize();
}
