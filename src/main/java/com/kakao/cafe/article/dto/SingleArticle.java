package com.kakao.cafe.article.dto;

import lombok.Builder;

@Builder
public class SingleArticle {

    private Long articleId;
    private String title;
    private String body;
    private String createdAt;
    private int viewCount;
    private Long authorId;
    private String authorName;
}
