package com.kakao.cafe.domain.question;

import com.kakao.cafe.dto.QuestionSaveDto;
import lombok.Getter;

@Getter
public class Question {
    private int id;
    private String title;
    private String writer;
    private String contents;

    public Question(int id, QuestionSaveDto questionSaveDto) {
        this.id = id;
        this.title = questionSaveDto.getTitle();
        this.writer = questionSaveDto.getWriter();
        this.contents = questionSaveDto.getContents();
    }
}
