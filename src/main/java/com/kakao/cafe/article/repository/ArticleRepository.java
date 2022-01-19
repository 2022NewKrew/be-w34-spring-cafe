package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.dto.ArticleCreateDTO;
import com.kakao.cafe.article.dto.ArticleUpdateDTO;

import java.util.List;

public interface ArticleRepository {
    public void addArticle(Article article);
    public void updateArticle(Long sequence, String title, String contents);
    public void deleteArticle(Article article);
    public List<Article> getArticlesNotDeleted();
    public Article getArticleByCondition(String key, String value);
    public void addReply(String userId, Long articleSeq, String contents);
}
