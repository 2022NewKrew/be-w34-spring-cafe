package com.kakao.cafe.Dto.Post;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostResponseDto {
    private final int id;
    private final String writer;
    private final String title;
    private final String content;
}
