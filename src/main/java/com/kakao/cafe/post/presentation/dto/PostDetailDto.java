package com.kakao.cafe.post.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class PostDetailDto {
    public static final int SUMMARY_SIZE = 20;

    private final Long id;
    private final String title;
    private final String content;
    private final String writerName;
    private final List<CommentDto> comments;
}
