package com.kakao.cafe.article.dto;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public class SingleArticle {

    private Long articleId;
    private String title;
    private String body;
    private LocalDateTime createdAt;
    private int viewCount;
    private Long authorId;
    private String authorName;
}
