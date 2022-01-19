package com.kakao.cafe.service;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.repository.JdbcTemplatesArticle;

import java.util.List;

public class ArticleService {
    private JdbcTemplatesArticle jdbcTemplatesArticle;

    public ArticleService(JdbcTemplatesArticle jdbcTemplatesArticle){
        this.jdbcTemplatesArticle = jdbcTemplatesArticle;
    }

    public void save(Article article){
        jdbcTemplatesArticle.save(article);
    }

    public List<Article> findAll(){
        return jdbcTemplatesArticle.findAll();
    }

    public Article findOneById(long id){
        return jdbcTemplatesArticle.findOneById(id);
    }

    public void update(Article article, long id) {
        jdbcTemplatesArticle.update(article, id);
    }

    public void delete(long id){
        jdbcTemplatesArticle.delete(id);
    }
}
