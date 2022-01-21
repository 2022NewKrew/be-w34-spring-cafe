package com.kakao.cafe.domain.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor @Builder
@Getter
public class CommentTableRowDto {
    private final Long id;
    private final String author;
    private final String content;
    private final String createdAt;
}
