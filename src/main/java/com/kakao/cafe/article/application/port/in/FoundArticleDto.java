package com.kakao.cafe.article.application.port.in;

import com.kakao.cafe.article.domain.ArticleId;
import java.time.Instant;

public class FoundArticleDto {

    private final ArticleId id;
    private final String title;
    private final String content;
    private final String createdAt;

    public FoundArticleDto(ArticleId id, String title, String content, Instant createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt.toString();
    }

    public ArticleId getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
