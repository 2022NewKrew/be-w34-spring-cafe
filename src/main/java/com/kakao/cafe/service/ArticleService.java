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
