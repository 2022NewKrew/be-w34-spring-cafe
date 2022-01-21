package com.kakao.cafe.article.dto;

import lombok.Builder;

@Builder
public class SingleComment {

    private Long commentId;
    private String body;
    private String createdAt;
    private Long authorId;
    private String authorName;
}
