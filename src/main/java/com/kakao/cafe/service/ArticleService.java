package com.kakao.cafe.service;


import com.kakao.cafe.domain.Article;
import com.kakao.cafe.dto.SampleArticleForm;
import com.kakao.cafe.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }


    public void addArticle(SampleArticleForm form){
        articleRepository.addArticle(form);
    }

    public Article findArticle(int articleID){
        return articleRepository.findArticle(articleID);
    }

    public List<Article> getArticles(){
        return articleRepository.getArticles();
    }
}
