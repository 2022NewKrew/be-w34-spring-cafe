package com.kakao.cafe.article.infra;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.domain.ArticleRepository;

import java.util.ArrayList;
import java.util.List;

//@Repository
public class ArticleRepositoryImpl implements ArticleRepository {

    public static final List<Article> currentArticles = new ArrayList<>();

    @Override
    public int save(Article article) {
        article.setId(currentArticles.size() + 1);
        currentArticles.add(article);
        return article.getId();
    }

    @Override
    public List<Article> findAll() {
        return currentArticles;
    }

    @Override
    public Article findByIdOrNull(int articleId) {
        return currentArticles.stream()
                .filter(article -> article.isSameArticleById(articleId))
                .findFirst().orElse(null);
    }

    @Override
    public void update(Article article) {

    }

    @Override
    public void delete(Article article) {

    }
}
