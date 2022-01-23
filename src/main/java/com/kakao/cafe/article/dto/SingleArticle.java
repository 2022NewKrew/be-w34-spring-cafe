package com.kakao.cafe.article.dto;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.user.domain.User;
import java.time.format.DateTimeFormatter;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SingleArticle {

    private Long articleId;
    private String title;
    private String body;
    private String createdAt;
    private int viewCount;
    private Long authorId;
    private String authorName;

    public static SingleArticle of(Article article, User author) {
        return SingleArticle.builder()
            .articleId(article.getId())
            .title(article.getTitle())
            .body(article.getBody())
            .createdAt(
                article.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
            .viewCount(article.getViewCount())
            .authorId(article.getAuthorId())
            .authorName(author.getNickname())
            .build();
    }
}
