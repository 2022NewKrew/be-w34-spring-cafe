package com.kakao.cafe.post.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreatePostRequest {
    private final String title;
    private final String writerName;
    private final String content;
}
