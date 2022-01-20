package com.kakao.cafe.domain.answer;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Answer {
    @Builder.Default
    private int id = -1;
    private int userId;
    private int questionId;
    private String contents;
    private String writer;
    private LocalDateTime createdAt;

    public Boolean isNew(){
        return id == -1;
    }
}
