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
    public final int commentCount;

    public static ArticleListResponse valueOf(Article article, int commentCount) {
        User author = article.getAuthor();
        return ArticleListResponse.builder()
                .articleId(article.getId())
                .authorId(author.getUserId())
                .authorName(author.getName())
                .title(article.getTitle())
                .createdAt(article.getCreatedAt())
                .commentCount(commentCount)
                .build();
    }
}
