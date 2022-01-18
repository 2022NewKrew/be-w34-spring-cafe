package com.kakao.cafe.domain.article;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
public class Article {
    private final long id;
    private final String title;
    private final String content;
    private final String createUserId;
    private final String createDate;
    private final int views;

    public Article(long id,String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createUserId = "unknown";
        this.createDate = LocalDate.now().toString();
        this.views = 0;
    }

    public Article(long id, String title, String content, String createUserId, String createDate, int views) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createUserId = createUserId;
        this.createDate = createDate;
        this.views = views;
    }

}
