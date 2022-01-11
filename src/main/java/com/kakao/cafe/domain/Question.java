package com.kakao.cafe.domain;

import com.kakao.cafe.dto.QuestionCreateRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class Question {
    private Long id;
    private User writer;
    private String title;
    private String contents;
    private LocalDateTime createdDateTime;

    @Builder
    public Question(Long id, User writer, String title, String contents, LocalDateTime createdDateTime) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdDateTime = createdDateTime;
    }

    public Question(QuestionCreateRequest questionCreateRequest, User writer) {
        createdDateTime = LocalDateTime.now();
        this.writer = writer;
        this.title = questionCreateRequest.getTitle();
        this.contents = questionCreateRequest.getContents();
    }

    //TODO
    public void setId(Long id) {
        this.id = id;
    }
}
