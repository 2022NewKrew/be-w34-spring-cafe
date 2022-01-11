package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.model.Article;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ArticleRepository {

    List<Article> articles = new ArrayList<>();

    public void save(Article article){
        articles.add(article);
    }

    public Long getNumberOfArticles(){
        return (long) articles.size();
    }

    public List<Article> findAll(){
        return articles;
    }

    public Article findOneById(Long id){
        return articles.stream().filter(article -> article.getId().equals(id))
                .collect(Collectors.toList())
                .get(0);
    }
}
