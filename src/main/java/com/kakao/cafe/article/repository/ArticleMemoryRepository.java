package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.model.Article;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ArticleMemoryRepository implements ArticleRepository{

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

    public Optional<Article> findOneById(Long id){
        return Optional.of(articles.stream().filter(article -> article.getId().equals(id))
                .collect(Collectors.toList())
                .get(0));
    }

    public void updateOne(Article article){}

    @Override
    public void deleteById(Long id) {}
}
