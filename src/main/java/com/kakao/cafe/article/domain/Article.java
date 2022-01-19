package com.kakao.cafe.article.domain;

import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Article {

    private Long id;
    private String title;
    private String body;
    private LocalDateTime createdAt;
    private int viewCount;
    private Long authorId;

    public boolean isAuthor(Long authorId) {
        return Objects.equals(this.authorId, authorId);
    }
}
