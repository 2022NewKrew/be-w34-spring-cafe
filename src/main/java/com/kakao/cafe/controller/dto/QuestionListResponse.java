package com.kakao.cafe.controller.dto;

import lombok.Builder;

public class QuestionListResponse {
    private Long questionId;
    private String title;
    private String createdDateTime;
    private String writer;
    private Long userId;
    private int numberOfReply;

    @Builder
    public QuestionListResponse(Long questionId, String title, String createdDateTime, String writer, Long userId, int numberOfReply) {
        this.questionId = questionId;
        this.title = title;
        this.createdDateTime = createdDateTime;
        this.writer = writer;
        this.userId = userId;
        this.numberOfReply = numberOfReply;
    }

}
