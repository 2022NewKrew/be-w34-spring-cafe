package com.kakao.cafe.web.dto;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@ToString
@Getter
public class ArticleRequest {
    private final long id;
    private final String title;
    private final String content;
    private final String createUserId;
    private final String createDate;
    private final int views;

    public ArticleRequest(String title, String content) {
        this.id = 0;
        this.title = title;
        this.content = content;
        this.createUserId = "unknown";
        this.createDate = LocalDate.now().toString();
        this.views = 0;
    }

}
