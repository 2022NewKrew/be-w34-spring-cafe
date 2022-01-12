package com.kakao.cafe.domain.question;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Question {
    private int id;
    private String title;
    private String writer;
    private String contents;
    private LocalDateTime createdAt;

    public Question(String title, String writer, String contents) {
        this.title = title;
        this.writer = writer;
        this.contents = contents;
        this.createdAt =  LocalDateTime.now();
    }

    public void setId(int id){
        this.id = id;
    }
}
