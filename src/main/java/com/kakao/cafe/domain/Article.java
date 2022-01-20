package com.kakao.cafe.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class Article {

    private int id;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime date;
    private List<Reply> replies;


    public Article(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public Article(int id, String title, String content, String writer, LocalDateTime date, List<Reply> replies) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.date = date;
        this.replies = replies;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

    public void generateId(int i) {
        this.id = i;
    }

    public boolean matchWriter(String writer) {
        return this.writer.equals(writer);
    }


}
