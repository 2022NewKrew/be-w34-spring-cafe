package com.kakao.cafe.article.dto;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.user.domain.User;
import java.time.format.DateTimeFormatter;
import lombok.Builder;

@Builder
public class MultipleArticle {

    private Long articleId;
    private String title;
    private String createdAt;
    private int viewCount;
    private Long authorId;
    private String authorName;

    public static MultipleArticle of(Article article, User author) {
        return MultipleArticle.builder()
            .articleId(article.getId())
            .title(article.getTitle())
            .createdAt(article.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
            .viewCount(article.getViewCount())
            .authorId(article.getAuthorId())
            .authorName(author.getNickname())
            .build();
    }
}
