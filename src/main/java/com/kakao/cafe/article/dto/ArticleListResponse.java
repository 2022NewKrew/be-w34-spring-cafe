package com.kakao.cafe.article.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kakao.cafe.article.domain.Article;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class ArticleListResponse {

    public final String articleId;
    public final String authorId;
    public final String authorName;
    public final String title;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd' 'HH:mm", timezone = "Asia/Seoul")
    public final LocalDateTime createdAt;

    public static ArticleListResponse valueOf(Article article) {
        return ArticleListResponse.builder()
                .articleId(article.getId())
                .authorId(article.getAuthor().getUserId())
                .title(article.getTitle())
                .createdAt(article.getCreatedAt())
                .build();
    }
}
