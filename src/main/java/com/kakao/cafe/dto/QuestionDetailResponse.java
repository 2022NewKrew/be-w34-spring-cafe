package com.kakao.cafe.dto;

import com.kakao.cafe.domain.Question;

import java.time.format.DateTimeFormatter;

public class QuestionDetailResponse {
    private Long userId;
    private String writer;
    private Long questionId;
    private String createdDateTime;
    private String title;
    private String contents;

    public QuestionDetailResponse(Question question, String writerNickname) {
        this.userId = question.getWriter();
        this.writer = writerNickname;
        this.questionId = question.getId();
        this.createdDateTime = question.getCreatedDateTime().format(DateTimeFormatter.ofPattern("YYYY-MM-DD HH:mm"));
        this.title = question.getTitle();
        this.contents = question.getContents();
    }
}
