package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.domain.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleMemoryRepository implements ArticleRepository {
    private List<Article> articles;

    public ArticleMemoryRepository(){
        articles = new ArrayList<>();
    }

    public void addArticle(Article article){
        articles.add(article);
    }

    public List<Article> getArticles(){
        return articles;
    }
}
