package com.kakao.cafe.dto;

import com.kakao.cafe.domain.Question;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
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
}
