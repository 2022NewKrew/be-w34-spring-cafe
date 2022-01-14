package com.kakao.cafe.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Articles {
    private final List<Article> articles = new ArrayList<>();

    public void addArticle(ArticleSaveDTO articleSaveDTO){
        Article article = new Article(articles.size()+1, articleSaveDTO);
        articles.add(article);
    }

    public Article findArticleById(int id){
        validateId(id);
        return articles.get(id-1);
    }

    public List<Article> getAllArticles(){
        return Collections.unmodifiableList(articles);
    }

    private void validateId(int id){
        if (id < 1 || id > articles.size()) {
            throw new IllegalArgumentException();
        }
    }
}
