package com.kakao.cafe.service;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.repository.ArticleRepository;

import java.util.List;

public class ArticleService {
    static private ArticleRepository articleRepository = new ArticleRepository();

    public void articleCreate(Article article){
        articleRepository.addArticle(article);
    }

    public Article getArticle(Long sequence){
        //validation 은 나중에 추가.
        return articleRepository.getAllAlticles().get(Math.toIntExact(sequence));
    }

    public Long getNextArticleSequence(){
        return Long.valueOf(articleRepository.getAllAlticles().size() + 1);
    }

    public List<Article> getAllArticles(){
        return articleRepository.getAllAlticles();
    }
}
