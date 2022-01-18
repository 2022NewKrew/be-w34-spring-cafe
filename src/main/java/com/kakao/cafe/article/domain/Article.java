package com.kakao.cafe.article.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
public class Article {
    private Long id;
    private String writer;
    private String title;
    private String contents;
    private String writingTime;
    private Long countOfComment;

    public Article(Long id, String writer, String title, String contents) {
        this.id = id;
        this.writer = writer;
        this.contents = contents;
        this.title = title;
        this.writingTime = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm"));
        this.countOfComment = 0L;
    }

    public Article(String writer, String contents, String title) {
        this.writer = writer;
        this.contents = contents;
        this.title = title;
        this.writingTime = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm"));
        this.countOfComment = 0L;
    }

    public Article(Long id, String writer, String title, String contents, String writingTime, Long countOfComment) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.writingTime = writingTime;
        this.countOfComment = countOfComment;
    }
}
