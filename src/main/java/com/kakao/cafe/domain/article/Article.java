package com.kakao.cafe.domain.article;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Getter
public class Article {
    private final long id;
    private final String title;
    private final String content;
    private final String createUserId;
    private final String createDate;
    private final int views;

    @Builder
    public Article(String title, String content) {
        this.id = 0;
        this.title = title;
        this.content = content;
        this.createUserId = "unknown";
        this.createDate = LocalDate.now().toString();
        this.views = 0;
    }

    private Article(long id, String title, String content, String createUserId, String createDate, int views) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createUserId = createUserId;
        this.createDate = createDate;
        this.views = views;
    }

    public static Article newInstance(long id, String title, String content, String createUserId, String createDate, int views) {
        return new Article(id, title, content, createUserId, createDate, views);
    }

}
