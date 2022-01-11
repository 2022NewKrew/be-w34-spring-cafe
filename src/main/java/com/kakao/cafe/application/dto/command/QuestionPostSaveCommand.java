package com.kakao.cafe.application.dto.command;

import com.kakao.cafe.domain.post.QuestionPost;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class QuestionPostSaveCommand {

    private final String title;
    private final String content;
    private final Long userAccountId;

    public QuestionPost toEntity() {
        return QuestionPost.builder()
                .title(title)
                .content(content)
                .createdAt(LocalDateTime.now())
                .viewCount(0)
                .userAccountId(userAccountId)
                .build();
    }
}
