package com.kakao.cafe.service;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.repository.ArticleRepository;

import java.util.List;

public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Long save(Article article) {
        return articleRepository.save(article);
    }


    public List<Article> getAllQuestions() {
        return articleRepository.getAllQuestions();
    }

    public Article findById(String id) {
        return articleRepository.findById(id);
    }

    public void deleteByWriter(String writer) {
        articleRepository.deleteByWriter(writer);
    }
}
