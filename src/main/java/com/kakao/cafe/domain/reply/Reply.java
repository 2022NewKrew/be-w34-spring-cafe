package com.kakao.cafe.domain.reply;

import com.kakao.cafe.domain.common.Deleted;
import com.kakao.cafe.domain.user.User;
import java.time.LocalDateTime;
import java.util.UUID;

public class Reply {
    private UUID replyId;
    private Comment comment;
    private final User writer;
    private LocalDateTime createdAt;
    private Deleted deleted;
    private UUID articleId;

    public Reply(UUID replyId, Comment comment, User writer, LocalDateTime createdAt, Deleted deleted, UUID articleId) {
        this.replyId = replyId;
        this.comment = comment;
        this.writer = writer;
        this.createdAt = createdAt;
        this.deleted = deleted;
        this.articleId = articleId;
    }

    public Reply(Comment comment, User writer, UUID articleId) {
        this(null, comment, writer, null, null, articleId);
    }

    public UUID getReplyId() {
        return replyId;
    }

    public Comment getComment() {
        return comment;
    }

    public User getWriter() {
        return writer;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Deleted getDeleted() {
        return deleted;
    }

    public UUID getArticleId() {
        return articleId;
    }

    public boolean isWriter(User user) {
        return writer.isUserSame(user);
    }
}
