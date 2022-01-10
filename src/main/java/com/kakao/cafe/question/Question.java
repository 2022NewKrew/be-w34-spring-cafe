package com.kakao.cafe.question;

import java.time.LocalDateTime;

public class Question {
    private Long id;
    private String writer;
    private String title;
    private String contents;
    private LocalDateTime createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void updateTime() {
        createTime = LocalDateTime.now();
    }
}
