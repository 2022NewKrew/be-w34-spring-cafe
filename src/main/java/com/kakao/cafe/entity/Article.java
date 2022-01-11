package com.kakao.cafe.entity;

import java.time.LocalDateTime;

public class Article extends BaseEntity {
    private final String writerName;
    private final String title;
    private final String contents;
    private final int numberOfReply;

    public Article(String writerName, String title, String contents, int numberOfReply, LocalDateTime createdDate) {
        super(createdDate);
        this.writerName = writerName;
        this.title = title;
        this.contents = contents;
        this.numberOfReply = numberOfReply;
    }

    public Long getArticleId() {
        return id;
    }

    public String getWriterName() {
        return writerName;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public int getNumberOfReply() {
        return numberOfReply;
    }
}
