package com.kakao.cafe.domain.question;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Question {
    @Builder.Default
    private int id = -1;
    private String title;
    private String writer;
    private String contents;
    private LocalDateTime createdAt;

    public Question update(Question question) {
        this.title = question.getTitle();
        this.writer = question.getWriter();
        this.contents = question.getContents();
        return this;
    }

    public Boolean isNew(){
        return id == -1;
    }
}
