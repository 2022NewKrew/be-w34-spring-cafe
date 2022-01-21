package com.kakao.cafe.dao.article;

import com.kakao.cafe.model.article.Article;
import java.util.List;
import java.util.Optional;

public interface ArticleDao {

    List<Article> getArticles(int pageNumber, int articlesPerPage);

    Article addArticle(Article article);

    Optional<Article> findArticleById(int id);

    int getSize();

    void deleteArticle(int id);

    void updateArticle(Article article);
}
