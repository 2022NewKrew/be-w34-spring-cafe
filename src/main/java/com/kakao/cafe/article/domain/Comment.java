package com.kakao.cafe.article.domain;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public class Comment {

    private Long id;
    private String body;
    private LocalDateTime createdAt;
    private Long authorId;
    private Long articleId;
}
