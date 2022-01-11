package com.kakao.cafe.dto;

import com.kakao.cafe.domain.Question;

import java.time.format.DateTimeFormatter;

public class QuestionListResponse {
    private Long questionId;
    private String title;
    private String createdDateTime;
    private String writer;
    private Long userId;
    private int numberOfReply;

    public QuestionListResponse(Question question, String writerNickname) {
        this.questionId = question.getId();
        this.title = question.getTitle();
        this.createdDateTime = question.getCreatedDateTime().format(DateTimeFormatter.ofPattern("YYYY-MM-DD HH:mm"));
        this.writer = writerNickname;
        this.userId = question.getWriter();
        this.numberOfReply = 99;
    }
}
