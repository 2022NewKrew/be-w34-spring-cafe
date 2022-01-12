package com.kakao.cafe.article.dto;

import java.time.LocalDate;
import lombok.Builder;

@Builder
public class MultipleArticle {

    private Long articleId;
    private String title;
    private LocalDate createdAt;
    private int viewCount;
    private Long authorId;
    private String authorName;
}
