package com.kakao.cafe.controller.dto;

import com.kakao.cafe.domain.Question;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionUpdateRequest {
    Long id;
    String title;
    String contents;

    public Question toEntity(Long userId){
        return Question.builder()
                .id(id)
                .writer(userId)
                .title(title)
                .contents(contents)
                .build();
    }
}
