package com.kakao.cafe.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ReplyRequestDTO {
    @NotNull
    private final Long articleId;
    @NotBlank
    private final String author;
    @NotBlank
    private final String content;

    public ReplyRequestDTO(Long articleId, String author, String content) {
        this.articleId = articleId;
        this.author = author;
        this.content = content;
    }

    public Long getArticleId() {
        return articleId;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }
}
