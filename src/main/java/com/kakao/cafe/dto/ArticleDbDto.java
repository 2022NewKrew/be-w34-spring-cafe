package com.kakao.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class ArticleDbDto {
    private final Long articleId;
    private final String writerName;
    private final Date writeTime;
    private final String title;
    private final String contents;
}
