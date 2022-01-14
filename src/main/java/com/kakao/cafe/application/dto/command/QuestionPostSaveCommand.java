package com.kakao.cafe.application.dto.command;

import com.kakao.cafe.domain.post.QuestionPost;
import com.kakao.cafe.domain.user.UserAccount;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class QuestionPostSaveCommand {

    private final String title;
    private final String content;
    private final Long userAccountId;

    public QuestionPost toEntity(UserAccount userAccount) {
        return QuestionPost.builder()
                .title(title)
                .content(content)
                .createdAt(LocalDateTime.now())
                .viewCount(0)
                .userAccount(userAccount)
                .build();
    }
}
