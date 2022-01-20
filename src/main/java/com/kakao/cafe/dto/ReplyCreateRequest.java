package com.kakao.cafe.dto;

import com.kakao.cafe.domain.Reply;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ReplyCreateRequest {
    Long questionId;
    String contents;

    public Reply toEntity(Long userId){
        return Reply.builder()
                .userId(userId)
                .questionId(questionId)
                .contents(contents)
                .createdDateTime(LocalDateTime.now())
                .build();
    }
}
