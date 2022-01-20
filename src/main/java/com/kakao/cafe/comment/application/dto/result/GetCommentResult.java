package com.kakao.cafe.comment.application.dto.result;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GetCommentResult {

    private final Long commentId;
    private final String author;
    private final String createdAt;
    private final String content;
    private final Long userAccountId;

}
