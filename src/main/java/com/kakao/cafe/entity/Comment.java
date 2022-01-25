package com.kakao.cafe.entity;

import com.kakao.cafe.util.Checker;
import org.springframework.lang.NonNull;

import java.time.Instant;
import java.util.Objects;

public class Comment {
    public static final int BODY_MIN = 1;
    public static final int BODY_MAX = 400;

    private final long idx;
    private final String userId;
    private final long articleIdx;
    private final String body;
    private final long createdAt;
    private final long modifiedAt;
    private final boolean deleted;

    public Comment(
            @NonNull final String userId,
            @NonNull final long articleIdx,
            @NonNull final String body
    ) throws IllegalArgumentException
    {
        validate(userId, articleIdx, body);
        this.idx = 0;
        this.userId = userId;
        this.articleIdx = articleIdx;
        this.body = body.trim();
        this.createdAt = Instant.now().getEpochSecond();
        this.modifiedAt = 0L;
        this.deleted = false;
    }

    private void validate(
            final String userId,
            final long articleIdx,
            final String body
    )
    {
        Checker.checkString("userId", userId, User.ID_REGEX, User.ID_MIN, User.ID_MAX);
        Checker.checkIndex(articleIdx);
        Checker.checkString("body", body, BODY_MIN, BODY_MAX);
    }

    public long getIdx() {
        return idx;
    }

    public String getUserId() {
        return userId;
    }

    public long getArticleIdx() {
        return articleIdx;
    }

    public String getBody() {
        return body;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public long getModifiedAt() {
        return modifiedAt;
    }

    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return idx == comment.idx && articleIdx == comment.articleIdx && createdAt == comment.createdAt && modifiedAt == comment.modifiedAt && deleted == comment.deleted && userId.equals(comment.userId) && body.equals(comment.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idx, userId, articleIdx, body, createdAt, modifiedAt, deleted);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "idx=" + idx +
                ", userId='" + userId + '\'' +
                ", articleIdx=" + articleIdx +
                ", body='" + body + '\'' +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                ", deleted=" + deleted +
                '}';
    }
}
