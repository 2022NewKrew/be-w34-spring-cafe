package com.kakao.cafe.service;

import com.kakao.cafe.dto.ArticleRegistrationDto;
import com.kakao.cafe.entity.Article;
import com.kakao.cafe.repository.ArticleRepository;

import java.util.List;

public class ArticleServiceImpl implements ArticleService{

    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public void write(ArticleRegistrationDto articleRequestDto) { articleRepository.createArticle(articleRequestDto);}

    @Override
    public List<Article> getArticles() {
        return articleRepository.readArticles();
    }

    @Override
    public Article findByArticleId(Integer articleId) {
        return articleRepository.readArticle(articleId);
    }
}
