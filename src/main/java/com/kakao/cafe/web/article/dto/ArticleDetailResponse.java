package com.kakao.cafe.web.article.dto;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.Content;
import com.kakao.cafe.domain.article.Title;

import java.sql.Timestamp;

public class ArticleDetailResponse {
    private final int articleId;
    private final Title title;
    private final Content content;
    private final Timestamp createdAt;
    private final Timestamp modifiedAt;

    public ArticleDetailResponse(Article article) {
        this.articleId = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt();
        this.modifiedAt = article.getModifiedAt();
    }

    public int getArticleId() {
        return articleId;
    }

    public Title getTitle() {
        return title;
    }

    public Content getContent() {
        return content;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getModifiedAt() {
        return modifiedAt;
    }
}
