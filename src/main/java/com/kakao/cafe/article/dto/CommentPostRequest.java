package com.kakao.cafe.article.dto;

import com.kakao.cafe.article.domain.Comment;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CommentPostRequest {

    @NotBlank
    @Size(min = 3, max = 500)
    private final String body;

    public Comment toEntity(Long authorId, Long articleId) {
        return Comment.builder()
            .body(body)
            .authorId(authorId)
            .articleId(articleId)
            .build();
    }
}
