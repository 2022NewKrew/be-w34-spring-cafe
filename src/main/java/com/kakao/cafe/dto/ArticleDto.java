package com.kakao.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ArticleDto {
    private Long articleId;
    private String title;
    private String content;
    private String writerEmail;
    private String writerUsername;
    private Long viewCount;
    private LocalDateTime regDate, modDate;
}
