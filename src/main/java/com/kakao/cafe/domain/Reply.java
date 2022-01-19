package com.kakao.cafe.domain;

import lombok.Data;

@Data
public class Reply {
    private Long id;
    private String writer;
    private String contents;
    private Long FK_article_id;
}
