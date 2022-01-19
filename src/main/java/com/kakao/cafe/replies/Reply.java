package com.kakao.cafe.replies;

import java.util.Objects;

public class Reply {
    private Long id;
    private String content;
    private Long articleId;
    private Long writerId;

    public Reply(Long id, String content, Long articleId, Long writerId) {
        this.id = id;
        this.content = content;
        this.articleId = articleId;
        this.writerId = writerId;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Long getArticleId() {
        return articleId;
    }

    public Long getWriterId() {
        return writerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reply reply = (Reply) o;
        return Objects.equals(id, reply.id) && Objects.equals(content, reply.content) && Objects.equals(articleId, reply.articleId) && Objects.equals(writerId, reply.writerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, articleId, writerId);
    }

    @Override
    public String toString() {
        return "Reply{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", articleId=" + articleId +
                ", writerId=" + writerId +
                '}';
    }
}
