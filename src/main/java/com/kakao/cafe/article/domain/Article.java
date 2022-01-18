package com.kakao.cafe.article.domain;

import java.time.LocalDateTime;
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

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
}
