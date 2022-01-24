package com.kakao.cafe.model.reply;

import com.kakao.cafe.model.user.UserId;
import java.time.LocalDateTime;

public class Reply {

    private final int id;
    private final int articleId;
    private final UserId userId;
    private final Comment comment;
    private final LocalDateTime createDate;
    private final boolean deleted;

    public Reply(int id, int articleId, UserId userId, Comment comment,
            LocalDateTime createDate, boolean deleted) {
        this.id = id;
        this.articleId = articleId;
        this.userId = userId;
        this.comment = comment;
        this.createDate = createDate;
        this.deleted = deleted;
    }

    public Reply(int id, int articleId, UserId userId, Comment comment) {
        this(id, articleId, userId, comment, LocalDateTime.now(), false);
    }

    public boolean isUserId(UserId userId) {
        return this.userId.equals(userId);
    }

    public int getId() {
        return id;
    }

    public int getArticleId() {
        return articleId;
    }

    public UserId getUserId() {
        return userId;
    }

    public Comment getComment() {
        return comment;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public boolean isDeleted() {
        return deleted;
    }
}
