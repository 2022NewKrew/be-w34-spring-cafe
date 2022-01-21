package com.kakao.cafe.domain;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class Article {

    private final Integer id;
    private final String title;
    private final User writer;
    private final String contents;
    private final LocalDateTime createdAt = LocalDateTime.now();
    private final LocalDateTime updatedAt = createdAt;

    public Article(Integer id, String title, User writer, String contents) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.contents = contents;
    }

    @Override
    public String toString() {
        return "Article{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", writer=" + writer +
            ", contents='" + contents + '\'' +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            '}';
    }
}
