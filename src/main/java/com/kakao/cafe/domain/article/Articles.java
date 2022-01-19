package com.kakao.cafe.domain.article;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Articles {

    private final List<Article> articleList;

    public Articles() {
        this.articleList = Collections.synchronizedList(new ArrayList<>());
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void add(Article article) {
        article.setArticleId(UUID.randomUUID());
        article.setCreatedAt(LocalDateTime.now());
        article.setViewCount(new ViewCount(0));
        article.setArticleDeleted(ArticleDeleted.from(false));
        articleList.add(article);
    }

    public Optional<Article> findByArticleId(UUID articleId) {
        synchronized (articleList) {
            return articleList.stream()
                    .filter((article) -> article.getArticleId().equals(articleId))
                    .findAny();
        }
    }

    public void update(Article article) {
        synchronized (articleList) {
            articleList.stream()
                    .filter((existingArticle) -> existingArticle.getArticleId().equals(article.getArticleId()))
                    .forEach((filteredArticle) -> filteredArticle.update(article));
        }
    }

    public void increaseViewCount(Article article) {
        synchronized (articleList) {
            articleList.stream()
                    .filter((existingArticle) -> existingArticle.getArticleId().equals(article.getArticleId()))
                    .forEach(Article::increaseViewCount);
        }
    }

    public void delete(Article article) {
        synchronized (articleList) {
            articleList.stream()
                    .filter((existingArticle) -> existingArticle.getArticleId().equals(article.getArticleId()))
                    .forEach(Article::delete);
        }
    }
}
