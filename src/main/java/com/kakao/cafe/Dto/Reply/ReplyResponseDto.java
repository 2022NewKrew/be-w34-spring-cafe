package com.kakao.cafe.Dto.Reply;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ReplyResponseDto {
    private final int id;
    private final String content;
    private final String writer;
}
