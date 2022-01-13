package com.kakao.cafe.web.service;

import com.kakao.cafe.web.domain.Article;
import com.kakao.cafe.web.repository.article.ArticleRepository;

import java.util.List;
import java.util.Optional;

public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article write(Article article) {
        articleRepository.save(article);
        return article;
    }

    public List<Article> findArticles() {
        return articleRepository.findAll();
    }

    public Optional<Article> findArticle(int id) {
        return articleRepository.findById(id);
    }
}
