package com.kakao.cafe.web.article.domain;

import java.util.ArrayList;
import java.util.List;

public class Articles {
    private List<Article> articleList = new ArrayList<>();

    public List<Article> getArticleList() {
        return articleList;
    }

    public void addQuestion(Article article){
        this.articleList.add(article);
        article.setId(articleList.size());
    }

    public Article findArticleByID(String id) {
        for (Article article : this.articleList) {
            if (article.getId() == Integer.valueOf(id))
                return article;
        }
        return null;
    }
}
