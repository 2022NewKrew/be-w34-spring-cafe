package com.kakao.cafe.domain.article;

import com.kakao.cafe.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Article {

    @Setter
    private UUID id;
    @Setter
    private LocalDateTime createdAt;

    private Title title;
    private Content content;
    private final User writer;
    private ViewCount viewCount;

    public Article(UUID id, Title title, Content content, User writer, LocalDateTime createdAt, ViewCount viewCount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.createdAt = createdAt;
        this.viewCount = viewCount;
    }

    public Article(Title title, Content content, User writer) {
        this(null, title, content, writer, null, new ViewCount());
    }

    public void increaseViewCount() {
        this.viewCount.increase();
    }

    public void update(Article article) {
        if (this.id.equals(article.getId())) {
            title = article.getTitle();
            content = article.getContent();
            viewCount = article.getViewCount();
        }
    }
}
