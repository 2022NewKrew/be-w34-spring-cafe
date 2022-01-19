package com.kakao.cafe.article.service;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.repository.ArticleRepository;
import com.kakao.cafe.util.ErrorCode;
import com.kakao.cafe.util.exception.ArticleException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article findByArticleId(Long articleId) {
        Optional<Article> article = articleRepository.findByArticleId(articleId);
        if (article.isEmpty()) {
            throw new ArticleException(ErrorCode.ARTICLE_NOT_FOUND);
        }
        return article.get();
    }

    public List<Article> findArticles() {
        return articleRepository.findAll();
    }

    public void update(Article article) {
        articleRepository.update(article);
    }

    public Article save(Article article) {
        return articleRepository.save(article);
    }

    public void delete(Long articleId) {
        articleRepository.delete(articleId);
    }

    public boolean isDuplicated(Long articleId) {
        return articleRepository.findByArticleId(articleId).isPresent();
    }
}
