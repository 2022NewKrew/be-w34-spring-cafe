package com.kakao.cafe.qna.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class Article {
    @Setter(AccessLevel.NONE)
    private final int id;

    @Setter(AccessLevel.NONE)
    private String writer;

    private String title;
    private String content;
    private String writtenTime;
    private int point;

    public Article(int id, String writer, String title, String content) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;

        this.writtenTime = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm"));
        this.point = 0;
    }
}
