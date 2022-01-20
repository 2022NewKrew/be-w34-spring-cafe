package com.kakao.cafe.comment.application.dto.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GetCommentCommand {

    private final Long commandId;
}
