package com.kakao.cafe.post.application.dto.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class QuestionPostDeleteCommand {

    private final Long postId;
}
