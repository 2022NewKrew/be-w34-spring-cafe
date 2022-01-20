package com.kakao.cafe.article.application.dto;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.user.domain.User;
import lombok.Builder;

@Builder
public class ArticleListResponse {

    public final int articleId;
    public final String authorId;
    public final String authorName;
    public final String title;
    public final String createdAt;

    public static ArticleListResponse valueOf(Article article) {
        User author = article.getAuthor();
        return ArticleListResponse.builder()
                .articleId(article.getId())
                .authorId(author.getUserId())
                .authorName(author.getName())
                .title(article.getTitle())
                .createdAt(article.getCreatedAt())
                .build();
    }
}
