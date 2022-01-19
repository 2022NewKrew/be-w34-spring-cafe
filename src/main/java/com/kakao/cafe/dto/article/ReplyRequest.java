package com.kakao.cafe.dto.article;

import com.kakao.cafe.domain.article.Reply;

public class ReplyRequest {

    private final String description;

    public ReplyRequest(String description) {
        this.description = description;
    }

    public Reply toEntity(Long articleId, Long authorId, String author) {
        return new Reply(articleId, authorId, author, description);
    }
}
