package com.kakao.cafe.article.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.user.domain.User;

import java.time.LocalDateTime;

public class ArticleListResponse {

    public final String articleId;
    public final String authorId;
    public final String authorName;
    public final String title;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd' 'HH:mm", timezone = "Asia/Seoul")
    public final LocalDateTime createdAt;

    public ArticleListResponse(String authorId, Article article) {
        this.articleId = article.getId();
        this.authorId = authorId;
        this.authorName = article.getAuthor().getName();
        this.title = article.getTitle();
        this.createdAt = article.getCreatedAt();
    }

    public static ArticleListResponse valueOf(Article article) {
        User author = article.getAuthor();
        String authorId = author.getUserId();
        return new ArticleListResponse(authorId, article);
    }
}
