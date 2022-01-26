package com.kakao.cafe.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ArticleList {
    private final List<Article> articleList = new ArrayList<>();

    public ArticleList() {}

    public void addArticle(Article article) {
        articleList.add(new Article(article));
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public Integer getArticleListSize() {
        return articleList.size();
    }

    public Article getArticleWithIndex(Integer index) {
        return articleList.get(index - 1);
    }
}

