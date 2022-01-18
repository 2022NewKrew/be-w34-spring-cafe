package com.kakao.cafe.post.application.dto.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class QuestionPostUpdateCommand {

    private final Long postId;
    private final String title;
    private final String content;

}
