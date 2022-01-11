package com.kakao.cafe.service;

import com.kakao.cafe.entiry.Article;
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

    public Article register(Article article) {
        return articleRepository.save(article);
    }

    public List<Article> getAllArticle(){
        return articleRepository.findAll();
    }
}
