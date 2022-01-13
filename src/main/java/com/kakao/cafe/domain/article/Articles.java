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
        articleList.add(article);
    }

    public Optional<Article> findByArticleId(UUID articleId) {
        synchronized (articleList) {
            return articleList.stream()
                    .filter((article) -> article.getArticleId().equals(articleId))
                    .findAny();
        }
    }
}
