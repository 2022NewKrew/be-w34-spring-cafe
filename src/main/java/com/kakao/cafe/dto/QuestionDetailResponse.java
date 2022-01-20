package com.kakao.cafe.dto;

import lombok.Builder;

public class QuestionDetailResponse {
    private Long userId;
    private String writer;
    private Long questionId;
    private String createdDateTime;
    private String title;
    private String contents;

    @Builder
    public QuestionDetailResponse(Long userId, String writer, Long questionId, String createdDateTime, String title, String contents) {
        this.userId = userId;
        this.writer = writer;
        this.questionId = questionId;
        this.createdDateTime = createdDateTime;
        this.title = title;
        this.contents = contents;
    }
}
