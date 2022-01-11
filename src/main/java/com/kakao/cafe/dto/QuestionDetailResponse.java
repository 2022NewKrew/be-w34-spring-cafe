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

    public QuestionDetailResponse(Question question) {
        this.userId = question.getWriter().getId();
        this.writer = question.getWriter().getNickname();
        this.questionId = question.getId();
        this.createdDateTime = question.getCreatedDateTime().format(DateTimeFormatter.ofPattern("YYYY-MM-DD HH:mm"));
        this.title = question.getTitle();
        this.contents = question.getContents();
    }
}
