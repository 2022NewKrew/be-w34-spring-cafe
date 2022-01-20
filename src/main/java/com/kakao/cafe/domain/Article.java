package com.kakao.cafe.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class Article {
    private long id;
    private String writer;
    private String title;
    private String contents;
    private String time;

    public Article(String writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        time = getCreatedTime();
    }

    private String getCreatedTime() {
        LocalDateTime nowDateTime = LocalDateTime.now();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return nowDateTime.format(dateFormatter);
    }
}
