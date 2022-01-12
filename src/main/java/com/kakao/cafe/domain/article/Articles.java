package com.kakao.cafe.domain.article;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Articles {

    private final List<Article> articleList;

    public Articles() {
        this.articleList = Collections.synchronizedList(new ArrayList<>());
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void add(Article article) {
        article.setArticleId(articleList.size() + 1);
        articleList.add(article);
    }

    public Optional<Article> findByArticleId(ArticleId articleId) {
        synchronized (articleList) {
            return articleList.stream()
                    .filter((article) -> article.getArticleId().equals(articleId))
                    .findAny();
        }
    }
}
