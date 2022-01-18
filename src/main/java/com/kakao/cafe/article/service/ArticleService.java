package com.kakao.cafe.article.service;

import com.kakao.cafe.article.model.Article;
import com.kakao.cafe.article.model.ArticleDTO;

import java.util.List;

public interface ArticleService {
    public List<Article> getArticles();
    public Article addArticle(Article article);
    public Article addArticle(ArticleDTO articleDTO);
    public Article readArticle(int id);
    public void updateArticle(int id, ArticleDTO articleDTO);
    public void deleteArticle(int id);
}
