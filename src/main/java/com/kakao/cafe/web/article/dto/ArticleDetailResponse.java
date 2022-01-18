package com.kakao.cafe.web.article.dto;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.Content;
import com.kakao.cafe.domain.article.Title;

import java.time.LocalDateTime;

public class ArticleDetailResponse {
    private final int articleId;
    private final Title title;
    private final Content content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }
}
