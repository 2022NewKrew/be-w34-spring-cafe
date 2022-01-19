package com.kakao.cafe.controller;

import com.kakao.cafe.domain.Reply;
import java.time.LocalDateTime;

public class ReplyDto {

    private int id;
    private String writer;
    private String content;
    private LocalDateTime date;
    private int articleId;

    public ReplyDto(int id, String writer, String content, LocalDateTime date, int articleId) {
        this.id = id;
        this.writer = writer;
        this.content = content;
        this.date = date;
        this.articleId = articleId;
    }

    public ReplyDto() {
    }

    public int getId() {
        return id;
    }

    public String getWriter() {
        return writer;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }


}
