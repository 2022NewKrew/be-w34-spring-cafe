package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.model.Article;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ArticleRepository {

    List<Article> articles = new ArrayList<>();

    public void save(Article article){
        articles.add(article);
    }

    public int getNumberOfArticles(){
        return articles.size();
    }

}
