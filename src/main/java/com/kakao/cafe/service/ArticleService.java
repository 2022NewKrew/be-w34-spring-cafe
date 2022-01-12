package com.kakao.cafe.service;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }


    public void save(Article article) {
        articleRepository.save(article);
    }

    public Article findArticle(Long id) {
        return articleRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("해당 id에 맞는 article이 존재하지 않습니다."));
    }

    public List<Article> findArticleList() {
        return articleRepository.findAll();
    }
}
