package com.kakao.cafe.replies;

public class ReplyDto {
    private final Long id;
    private final String content;
    private final Long articleId;
    private final Long writerId;
    private final boolean status;

    public ReplyDto(Long id, String content, Long articleId, Long writerId, boolean status) {
        this.id = id;
        this.content = content;
        this.articleId = articleId;
        this.writerId = writerId;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Long getWriterId() {
        return writerId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public boolean isStatus() {
        return status;
    }

    public static ReplyDto toDto(Reply reply) {
        return new ReplyDto(reply.getId(), reply.getContent(), reply.getArticleId(), reply.getWriterId(), reply.isStatus());
    }
}
