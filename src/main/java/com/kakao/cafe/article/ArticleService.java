package com.kakao.cafe.article;

import java.util.ArrayList;
import java.util.List;

public class ArticleService {
    private Long id = 0L;
    private final List<Articles> articleList = new ArrayList<>();

    public List<Articles> getArticleList() { return articleList; }

    public void createArticle(Articles article) {
        article.setId(id);
        id++;
        articleList.add(article);
    }

    public Articles getArticleById(Long id) {
        for (Articles article: articleList) {
            if (article.getId() == id)
                return article;
        }
        return null;
    }
}
