package com.kakao.cafe.service;

import com.kakao.cafe.Repository.ArticleRepository;
import com.kakao.cafe.domain.Article;

public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository){
        this.articleRepository = articleRepository;
    }

    public void save(Article article) {
        articleRepository.save(article);
    }
}
