package com.kakao.cafe.dto;

import com.kakao.cafe.domain.Question;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class QuestionCreateRequest {
    private String writer;
    private String title;
    private String contents;

    public Question toEntity(Long userId, LocalDateTime dateTime){
        return Question.builder()
                .writer(userId)
                .title(title)
                .contents(contents)
                .createdDateTime(dateTime).build();
    }
}
