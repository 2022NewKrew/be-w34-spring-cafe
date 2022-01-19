package com.kakao.cafe.dto.article;

public class ReplyDto {

    private final Long replyId;
    private final Long articleId;
    private final Long authorId;
    private final String author;
    private final String description;

    public ReplyDto(Long replyId, Long articleId, Long authorId, String author, String description) {
        this.replyId = replyId;
        this.articleId = articleId;
        this.authorId = authorId;
        this.author = author;
        this.description = description;
    }
}
