package com.kakao.cafe.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {
    private long articleId;
    private String title;
    private String writerId;
    private String content;
    private LocalDateTime createdDate;
    private String formattedCreatedDate;
    private Long hits;
    private int commentsCount;
    boolean isDeleted;
}

