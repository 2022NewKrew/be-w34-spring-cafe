package com.kakao.cafe.article.domain;

import com.kakao.cafe.user.domain.User;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class Article {

    private final Integer aid;
    private final String title;
    private final User writer;
    private final String contents;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public Article(Integer aid, String title, User writer, String contents, LocalDateTime createdAt,
        LocalDateTime updatedAt) {
        this.aid = aid;
        this.title = title;
        this.writer = writer;
        this.contents = contents;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Article{" +
            "title='" + title + '\'' +
            ", author=" + writer +
            ", updatedAt=" + updatedAt +
            '}';
    }
}
