package com.kakao.cafe.service;

import com.kakao.cafe.dto.ArticleRegistrationDto;
import com.kakao.cafe.entity.Article;
import com.kakao.cafe.repository.ArticleRepository;
import com.kakao.cafe.repository.JdbcArticleRepository;

import java.util.List;

public class ArticleServiceImpl implements ArticleService{

    private final JdbcArticleRepository articleRepository;

    public ArticleServiceImpl(JdbcArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public void write(ArticleRegistrationDto articleRequestDto) { articleRepository.create(articleRequestDto);}

    @Override
    public List<Article> getArticles() {
        return articleRepository.readAll();
    }

    @Override
    public Article findByArticleId(Integer articleId) {
        return articleRepository.readById(articleId);
    }
}
