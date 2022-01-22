package com.kakao.cafe.dto;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class ArticleViewDto {

    private final Integer id;
    private final String title;
    private final String writer;
    private final String contents;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public ArticleViewDto(Integer id, String title, String writer, String contents,
        LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.contents = contents;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "ArticleViewDto{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", writer='" + writer + '\'' +
            ", contents='" + contents + '\'' +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            '}';
    }
}
