package com.kakao.cafe.Dto.Post;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class PostRequestDto {
    private final String writer;
    private final String title;
    private final String content;
}
