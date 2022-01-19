package com.kakao.cafe.article.dto;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public class SingleComment {

    private Long commentId;
    private String body;
    private LocalDateTime createdAt;
    private Long authorId;
    private String authorName;
}
