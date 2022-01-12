package com.kakao.cafe.article.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.user.domain.User;

import java.time.LocalDateTime;

public class ArticleShowResponse {

    public final String authorId;
    public final String authorName;
    public final String title;
    public final String content;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd' 'HH:mm", timezone = "Asia/Seoul")
    public final LocalDateTime createdAt;

    public ArticleShowResponse(User author, Article article) {
        this.authorId = author.getUserId();
        this.authorName = author.getName();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt();
    }

    public static ArticleShowResponse valueOf(Article article) {
        return new ArticleShowResponse(article.getAuthor(), article);
    }
}
