package com.kakao.cafe.repository.article;

import com.kakao.cafe.domain.article.Article;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

public class ArticleList {
    private final List<Article> articleList = new ArrayList<>();

    private ArticleList() {

    }

    private static class InnerInstanceClass {
        private static final ArticleList instance = new ArticleList();
    }


    public static ArticleList getInstance() {
        return ArticleList.InnerInstanceClass.instance;
    }

    protected List<Article> getList() {
        return List.copyOf(articleList);
    }

    protected void addArticle(Article article) {
        articleList.add(article);
    }

    public int getSize() {
        return articleList.size();
    }

    protected Article findById(Long articleId) {
        Article target = articleList.stream().filter(article -> article.hasId(articleId)).findFirst().orElse(null);
        Assert.notNull(target, "FIND Error: Null Article Id in List");
        return target;
    }
}

