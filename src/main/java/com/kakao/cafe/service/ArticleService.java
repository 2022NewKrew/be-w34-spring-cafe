package com.kakao.cafe.service;

import com.kakao.cafe.domain.dto.ArticleCreateCommand;
import com.kakao.cafe.domain.entity.Article;
import com.kakao.cafe.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    private ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void createArticle(ArticleCreateCommand acc) {
        articleRepository.store(acc);
    }

    public Article getArticle(Long id) {
        return articleRepository.retrieve(id);
    }

    public List<Article> getAllArticles() {
        return articleRepository.toList();
    }
}
