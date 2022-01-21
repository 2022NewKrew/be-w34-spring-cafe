package com.kakao.cafe.domain.comment.dto;

import com.kakao.cafe.domain.comment.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class CommentCreateDto {
    private final Long userId;
    private final Long articleId;
    private final String content;

    public Comment toEntity() {
        return Comment.builder()
                .userId(userId)
                .articleId(articleId)
                .content(content)
                .build();
    }
}
