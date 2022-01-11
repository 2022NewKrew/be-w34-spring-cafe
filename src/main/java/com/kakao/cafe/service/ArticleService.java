package com.kakao.cafe.service;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.repository.ArticleMemoryRepository;
import com.kakao.cafe.repository.ArticleRepository;

import java.util.List;

public class ArticleService {
    private ArticleRepository articleRepository = new ArticleMemoryRepository();

    public void articleCreate(Article article){
        setNextArticleSequence(article); //고유번호지정
        articleRepository.addArticle(article);
    }

    public Article getArticle(Long sequence){
        //validation 은 나중에 추가.
        return articleRepository.getArticles().get(Math.toIntExact(sequence));
    }
    private void setNextArticleSequence(Article article){
        if(article == null){
            return;
        }
        article.setSequence(Long.valueOf(articleRepository.getArticles().size() + 1));
    }

    public List<Article> getAllArticles(){
        return articleRepository.getArticles();
    }
}
