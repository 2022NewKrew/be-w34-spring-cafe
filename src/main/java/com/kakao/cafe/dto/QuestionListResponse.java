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

    public QuestionListResponse(Question question) {
        this.questionId = question.getId();
        this.title = question.getTitle();
        this.createdDateTime = question.getCreatedDateTime().format(DateTimeFormatter.ofPattern("YYYY-MM-DD HH:mm"));
        this.writer = question.getWriter().getNickname();
        this.userId = question.getWriter().getId();
        this.numberOfReply = 99;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getNumberOfReply() {
        return numberOfReply;
    }

    public void setNumberOfReply(int numberOfReply) {
        this.numberOfReply = numberOfReply;
    }
}
