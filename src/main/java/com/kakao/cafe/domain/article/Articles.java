package com.kakao.cafe.domain.article;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Articles {

    private final List<Article> articleList;

    public Articles() {
        this.articleList = new ArrayList<>();
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void add(Article article) {
        article.setArticleId(articleList.size() + 1);
        articleList.add(article);
    }

    public Optional<Article> findByArticleId(ArticleId articleId) {
        return articleList.stream()
                .filter((article) -> article.getArticleId().equals(articleId))
                .findAny();
    }
}
