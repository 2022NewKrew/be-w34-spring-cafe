package com.kakao.cafe.domain.question;

import com.kakao.cafe.dto.QuestionSaveDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Question {
    private int id;
    private String title;
    private String writer;
    private String contents;
    private LocalDateTime createdDateTime;

    public Question(int id, QuestionSaveDto questionSaveDto) {
        this.id = id;
        this.title = questionSaveDto.getTitle();
        this.writer = questionSaveDto.getWriter();
        this.contents = questionSaveDto.getContents();
        this.createdDateTime =  LocalDateTime.now();
    }
}
