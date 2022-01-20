package com.kakao.cafe.Dto.Post;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PostResponseDto {
    private final Long id;
    private final String writer;
    private final String title;
    private final String content;
}
