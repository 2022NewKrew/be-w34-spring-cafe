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
    private Long writer;
    private String title;
    private String contents;
    private LocalDateTime createdDateTime;

    @Builder
    public Question(Long id, Long writer, String title, String contents, LocalDateTime createdDateTime) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdDateTime = createdDateTime;
    }

    //TODO
    public void setId(Long id) {
        this.id = id;
    }
}
