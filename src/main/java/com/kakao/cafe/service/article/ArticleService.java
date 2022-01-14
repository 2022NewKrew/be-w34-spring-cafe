package com.kakao.cafe.service.article;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.ArticleList;

import java.util.List;

public class ArticleService {
    private static int articleIdCount = 1;

    private ArticleService() {

    }

    private static class InnerInstanceClass {
        private static final ArticleService instance = new ArticleService();
    }

    public static ArticleService getInstance() {
        return ArticleService.InnerInstanceClass.instance;
    }

    public String getArticleId() {
        return String.valueOf(articleIdCount++);
    }

    public static void postArticle(Article article) {
        ArticleList.getInstance().addArticle(article);
    }

    public static ArticleList getArticleList() {
        return ArticleList.getInstance();
    }

}
