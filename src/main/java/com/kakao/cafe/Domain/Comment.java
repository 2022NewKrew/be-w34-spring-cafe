package com.kakao.cafe.Domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Getter
@Setter
public class Comment {
    private Long id;
    private Timestamp created;

    @NotBlank
    private String author;
    @NotBlank
    private String content;
    @NotBlank
    private Long articleId;

    public Comment(String content, Long articleId) {
        this.content = content;
        this.articleId = articleId;
    }
}
