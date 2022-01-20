package com.kakao.cafe.dto;

import com.kakao.cafe.domain.Question;
import lombok.Builder;

import java.time.format.DateTimeFormatter;


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

//    public QuestionListResponse(Question question, String writerNickname) {
//        this.questionId = question.getId();
//        this.title = question.getTitle();
//        this.createdDateTime = question.getCreatedDateTime().format(DateTimeFormatter.ofPattern("YYYY-MM-DD HH:mm"));
//        this.writer = writerNickname;
//        this.userId = question.getWriter();
//        this.numberOfReply = 99;
//    }
}
