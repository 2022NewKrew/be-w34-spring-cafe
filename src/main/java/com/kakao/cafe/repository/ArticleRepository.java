package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleRepository {
    static private List<Article> articles = new ArrayList<>();

    public void addArticle(Article article){
        articles.add(article);
    }

    public List<Article> getArticles(){
        return articles;
    }
}
