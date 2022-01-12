package com.kakao.cafe.article.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kakao.cafe.article.domain.Article;

import java.time.LocalDateTime;

public class ArticleListResponse {

    public final String authorName;
    public final String title;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    public final LocalDateTime createdAt;

    public ArticleListResponse(Article article) {
        authorName = article.getAuthor().getName();
        title = article.getTitle();
        createdAt = article.getCreatedAt();
    }

    public static ArticleListResponse valueOf(Article article) {
        return new ArticleListResponse(article);
    }
}
