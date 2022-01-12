package com.kakao.cafe.article.infra;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.domain.ArticleRepository;

import java.util.ArrayList;
import java.util.List;

public class ArticleRepositoryImpl implements ArticleRepository {

    public static final List<Article> currentArticles = new ArrayList<>();

    @Override
    public Article save(Article article) {
        currentArticles.add(article);
        return article;
    }

    @Override
    public List<Article> findAll() {
        return currentArticles;
    }

    @Override
    public Article findByIdOrNull(String articleId) {
        return currentArticles.stream()
                .filter(article -> article.isSameArticleById(articleId))
                .findFirst().orElse(null);
    }
}
