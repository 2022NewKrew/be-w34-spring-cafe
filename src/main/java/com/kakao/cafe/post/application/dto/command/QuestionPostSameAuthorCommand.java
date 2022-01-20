package com.kakao.cafe.post.application.dto.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class QuestionPostSameAuthorCommand {

    private final Long questionPostId;
    private final Long userAccountId;
}
