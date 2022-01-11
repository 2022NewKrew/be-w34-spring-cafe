package com.kakao.cafe.service;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }


    public void save(Article article) {
        articleRepository.save(article);
    }

    public List<Article> findArticleList() {
        return articleRepository.findAll();
    }
}
