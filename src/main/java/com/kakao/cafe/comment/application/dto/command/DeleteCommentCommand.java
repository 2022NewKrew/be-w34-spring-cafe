package com.kakao.cafe.comment.application.dto.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DeleteCommentCommand {

    private final Long commentId;

}
