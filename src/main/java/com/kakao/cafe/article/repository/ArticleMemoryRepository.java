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

    @Override
    public void updateArticle(Long sequence, String title, String contents) {

    }


    @Override
    public void deleteArticle(Article article) {

    }


    public List<Article> getArticlesNotDeleted(){
        return articles;
    }

    @Override
    public Article getArticleByCondition(String key, String value) {
        return null;
    }
}
