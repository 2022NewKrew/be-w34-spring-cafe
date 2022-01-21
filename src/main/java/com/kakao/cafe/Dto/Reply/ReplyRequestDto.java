package com.kakao.cafe.Dto.Reply;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ReplyRequestDto {
    private final String content;
    private final String writer;
    private final int postId;
}
