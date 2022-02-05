package com.kakao.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
public class ArticleDto {
    private final Long articleId;
    private final String writer;
    private final Date time;
    private final String title;
    private final String contents;
    private final Integer commentsCount;
    private final List<CommentDto> comments;
}
