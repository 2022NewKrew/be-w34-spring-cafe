package com.kakao.cafe.web.article.dto;

import com.kakao.cafe.domain.article.Article;

public class ArticleDetailResponse {
    private final int articleId;
    private final String title;
    private final String content;

    public ArticleDetailResponse(Article article) {
        this.articleId = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
    }

    public int getArticleId() {
        return articleId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
