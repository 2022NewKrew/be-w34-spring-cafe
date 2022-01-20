package com.kakao.cafe.repository.article;

import com.kakao.cafe.domain.article.Article;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        return List.copyOf(this.articleList);
    }

    protected void addArticle(Article article) {
        this.articleList.add(article);
    }

    public int getSize() {
        return this.articleList.size();
    }

    protected Optional<Article> findById(Long articleId) {
        Optional<Article> target = this.articleList.stream().filter(article -> article.hasId(articleId)).findFirst();
        Assert.notNull(target, "FIND Error: Null Article Id in List");
        return target;
    }
}

