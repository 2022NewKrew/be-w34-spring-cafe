package com.kakao.cafe.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Question {
    private Long id;
    private Long writer;
    private String title;
    private String contents;
    private LocalDateTime createdDateTime;
    private Boolean isDeleted;

    @Builder
    public Question(Long id, Long writer, String title, String contents, LocalDateTime createdDateTime, Boolean isDeleted) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdDateTime = createdDateTime;
        this.isDeleted = isDeleted;
    }

}
