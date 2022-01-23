package com.kakao.cafe.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {
    boolean isDeleted;
    private long articleId;
    private String title;
    private String writerId;
    private String content;
    private LocalDateTime createdDate;
    private String formattedCreatedDate;
    private Long hits;
    private int commentsCount;
}

