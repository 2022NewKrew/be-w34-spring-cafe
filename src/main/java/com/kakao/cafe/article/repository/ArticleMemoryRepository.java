package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.dto.ArticleCreateDTO;

import java.util.ArrayList;
import java.util.List;

public class ArticleMemoryRepository implements ArticleRepository {
    private List<Article> articles;

    public ArticleMemoryRepository(){
        articles = new ArrayList<>();
    }

    public void addArticle(Article article){
//        Article article = new Article(articleCreateDTO, Long.valueOf(articles.size() + 1));
//        articles.add(article);
    }

    public List<Article> getArticles(){
        return articles;
    }

    @Override
    public Article getArticleByCondition(String key, String value) {
        return null;
    }
}
