package com.kakao.cafe.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void createArticle(Article article) {
        articleRepository.save(article);
    }

    public Article findByTitle(String title) {
        return articleRepository.findByTitle(title).orElseThrow();
    }

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }
}
