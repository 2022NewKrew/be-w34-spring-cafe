package com.kakao.cafe.model.Post;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PostResponseDto {
    private final Long id;
    private final String title;
    private final String content;
}
