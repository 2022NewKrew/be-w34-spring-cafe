package com.kakao.cafe.dto;

import java.time.LocalDateTime;

public class ArticleDto {
    private final int id;
    private final String title;
    private final String writer;
    private final String contents;
    private final LocalDateTime createDate;

    public ArticleDto(int id, String title, String writer, String contents, LocalDateTime localDateTime) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.contents = contents;
        this.createDate = localDateTime;
    }

    public String getTitle() {
        return title;
    }

    public String getWriter() {
        return writer;
    }

    public String getContents() {
        return contents;
    }
}
