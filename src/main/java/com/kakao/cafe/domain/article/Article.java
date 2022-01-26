package com.kakao.cafe.domain.article;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Article {
    private final long id;
    private final String title;
    private final String content;
    private final String createUserId;
    private final String createDate;
    private final int views;

    @Builder
    private Article(long id, String title, String content, String createUserId, String createDate, int views) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createUserId = createUserId;
        this.createDate = createDate;
        this.views = views;
    }
}
