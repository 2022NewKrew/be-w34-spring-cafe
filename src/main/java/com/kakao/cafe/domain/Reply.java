package com.kakao.cafe.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Reply {

    Long id;
    Long questionId;
    Long userId;
    LocalDateTime createdDateTime;
    String contents;
    Boolean isDeleted;

    @Builder
    public Reply(Long id, Long questionId, Long userId, LocalDateTime createdDateTime, String contents, Boolean isDeleted) {
        this.id = id;
        this.questionId = questionId;
        this.userId = userId;
        this.createdDateTime = createdDateTime;
        this.contents = contents;
        this.isDeleted = isDeleted;
    }
}
