package com.kakao.cafe.article.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class ArticleReply {
    private int id;
    private int articleId;
    private String writer;
    private String contents;
    private LocalDateTime createTime;
}

