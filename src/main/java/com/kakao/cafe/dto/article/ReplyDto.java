package com.kakao.cafe.dto.article;

import com.kakao.cafe.domain.article.Reply;

public class ReplyDto {

    private final Long replyId;
    private final Long articleId;
    private final Long authorId;
    private final String author;
    private final String description;

    public ReplyDto(Reply reply) {
        this.replyId = reply.getId();
        this.articleId = reply.getArticleId();
        this.authorId = reply.getAuthorId();
        this.author = reply.getAuthor();
        this.description = reply.getDescription();
    }

    public Long getAuthorId() {
        return authorId;
    }
}
