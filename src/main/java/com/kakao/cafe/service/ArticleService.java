package com.kakao.cafe.service;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.ArticleId;
import com.kakao.cafe.exception.NoSuchArticleException;
import com.kakao.cafe.repository.ArticleRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void registerArticle(Article article) {
        articleRepository.save(article);
    }

    public List<Article> getArticleList() {
        return articleRepository.findAll();
    }

    public Article findArticleById(ArticleId articleId) {
        Article article = articleRepository.findArticleById(articleId)
                .orElseThrow(() -> new NoSuchArticleException("해당 게시글을 찾을 수 없습니다."));
        article.increaseViewCount();
        return article;
    }
}
