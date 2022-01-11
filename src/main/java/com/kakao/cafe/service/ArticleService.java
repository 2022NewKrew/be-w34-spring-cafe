package com.kakao.cafe.service;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.repository.ArticleRepository;

import java.util.List;

public class ArticleService {
    static private ArticleRepository articleRepository = new ArticleRepository();

    public void articleCreate(Article article){
        articleRepository.addArticle(article);
    }

    public List<Article> getAllArticles(){
        return articleRepository.getAllAlticles();
    }
}
