package com.kakao.cafe.post.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class PostDetailDto {
    private final Long id;
    private final String title;
    private final String content;
    private final String writerName;
    private final List<String> comments;
}
