package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.domain.Article;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArticleMemoryRepository implements ArticleRepository {
    private Long index;
    private List<Article> articles;

    public ArticleMemoryRepository() {
        index = 0L;
        articles = new ArrayList<>();
    }

    public Article save(Article article) {
        Article saveArticle = new Article(++index, article);
        articles.add(saveArticle);
        return saveArticle;
    }

    public Optional<Article> findByArticleId(Long articleId) {
        return articles.stream()
                .filter(article -> article.getArticleId().equals(articleId))
                .findAny();
    }

    public List<Article> findAll() {
        return articles;
    }
}
