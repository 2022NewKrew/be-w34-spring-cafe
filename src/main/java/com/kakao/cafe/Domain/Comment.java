package com.kakao.cafe.Domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Comment {
    private Long id;
    private String created;

    private String author;
    private String content;
    private Long articleId;

    public Comment(String content, Long articleId) {
        this.content = content;
        this.articleId = articleId;
    }
}
