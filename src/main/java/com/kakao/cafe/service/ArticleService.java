package com.kakao.cafe.service;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.repository.ArticleRepository;
import com.kakao.cafe.utility.ArticleException;
import com.kakao.cafe.utility.ErrorCode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article postArticle(Article article) {
        validateNull(article);
        validateDuplicateArticle(article);
        return articleRepository.save(article);
    }

    private void validateNull(Article article) {
        if (article == null) {
            throw new ArticleException(ErrorCode.INVALID_NULL_VALUE);
        }
    }

    private void validateDuplicateArticle(Article article) {
        boolean isDuplicated = articleRepository.findByArticleId(article.getArticleId()).isPresent();
        if (isDuplicated) {
            throw new ArticleException(ErrorCode.DUPLICATE_ARTICLE_ID);
        }
    }

    public Article findArticleByArticleId(Long articleId) {
        Optional<Article> article = articleRepository.findByArticleId(articleId);
        if (article.isEmpty()) {
            throw new ArticleException(ErrorCode.ARTICLE_NOT_FOUND);
        }
        return article.get();
    }

    public List<Article> findArticles() {
        return articleRepository.findAll();
    }
}
