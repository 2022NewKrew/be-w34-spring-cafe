package com.kakao.cafe.domain;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class Article {

    private final Integer aid;
    private final String title;
    private final User writer;
    private final String contents;
    private final LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = createdAt;

    public Article(Integer aid, String title, User writer, String contents) {
        this.aid = aid;
        this.title = title;
        this.writer = writer;
        this.contents = contents;
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
