package com.kakao.cafe.article.service;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.dto.ArticleCreateDTO;
import com.kakao.cafe.article.repository.ArticleMemoryRepository;
import com.kakao.cafe.article.repository.ArticleRepository;

import java.util.List;

public class ArticleService {
    private ArticleRepository articleRepository = new ArticleMemoryRepository();

    public void articleCreate(ArticleCreateDTO articleCreateDTO){
        articleRepository.addArticle(articleCreateDTO);
    }

    public Article getArticle(Long sequence){
        //validation 은 나중에 추가.
        return articleRepository.getArticles().get(Math.toIntExact(sequence));
    }

    public List<Article> getAllArticles(){
        return articleRepository.getArticles();
    }
}
