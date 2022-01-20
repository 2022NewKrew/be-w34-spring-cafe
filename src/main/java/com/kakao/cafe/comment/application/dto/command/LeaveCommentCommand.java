package com.kakao.cafe.comment.application.dto.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LeaveCommentCommand {

    private final String content;
    private final Long userAccountId;
    private final Long questionPostId;
}
