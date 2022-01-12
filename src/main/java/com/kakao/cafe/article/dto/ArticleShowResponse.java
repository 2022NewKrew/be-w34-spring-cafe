package com.kakao.cafe.article.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.user.domain.User;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class ArticleShowResponse {

    public final String authorId;
    public final String authorName;
    public final String title;
    public final String content;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd' 'HH:mm", timezone = "Asia/Seoul")
    public final LocalDateTime createdAt;

    public static ArticleShowResponse valueOf(Article article) {
        User author = article.getAuthor();
        return ArticleShowResponse.builder()
                .authorId(author.getUserId())
                .authorName(author.getName())
                .title(article.getTitle())
                .content(article.getContent())
                .createdAt(article.getCreatedAt())
                .build();


    }
}
