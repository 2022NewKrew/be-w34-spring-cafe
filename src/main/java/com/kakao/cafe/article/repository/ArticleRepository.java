package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.dto.ArticleCreateDTO;

import java.util.List;

public interface ArticleRepository {
    public void addArticle(ArticleCreateDTO articleCreateDTO);
    public List<Article> getArticles();
    public Article getArticleByCondition(String key, String value);
}
