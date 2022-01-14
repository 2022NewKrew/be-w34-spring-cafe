package com.kakao.cafe.infra.dao;

import java.time.LocalDateTime;

public class ArticleCreateCommand {
    private Long id;
    private final LocalDateTime createdTime;
    private final String writerName;
    private final String title;
    private final String contents;
    private final int numberOfReply;

    public ArticleCreateCommand(LocalDateTime createdTime, String writerName, String title, String contents, int numberOfReply) {
        this.createdTime = createdTime;
        this.writerName = writerName;
        this.title = title;
        this.contents = contents;
        this.numberOfReply = numberOfReply;
    }

    public ArticleCreateCommand(Long id, LocalDateTime createdTime, String writerName, String title, String contents, int numberOfReply) {
        this.id = id;
        this.createdTime = createdTime;
        this.writerName = writerName;
        this.title = title;
        this.contents = contents;
        this.numberOfReply = numberOfReply;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
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
