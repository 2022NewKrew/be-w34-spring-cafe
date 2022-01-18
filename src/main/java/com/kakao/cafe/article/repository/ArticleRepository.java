package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.dto.ArticleCreateDTO;

import java.util.List;

public interface ArticleRepository {
    public void addArticle(Article article);
    public void deleteArticle(Article article);
    public List<Article> getArticlesNotDeleted();
    public Article getArticleByCondition(String key, String value);
}
