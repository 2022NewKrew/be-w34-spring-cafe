package com.kakao.cafe.article.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ArticleReadResponseDTO {
    public Long articleId;
    public Long authorId;
    public LocalDateTime makeTime;
    public Integer hits;
    public String contents;
    public String title;
    public String authorStringId;
}
