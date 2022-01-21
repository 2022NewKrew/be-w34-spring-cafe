package com.kakao.cafe.entity;

import com.kakao.cafe.util.Checker;
import org.springframework.lang.NonNull;

import java.time.Instant;
import java.util.Objects;

public class Article {
    public static final int TITLE_MIN = 1;
    public static final int TITLE_MAX = 255;
    public static final int BODY_MIN = 1;
    public static final int BODY_MAX = 4095;

    private final long idx;
    private final String userId;
    private final String title;
    private final String body;
    private final long createdAt;
    private final long modifiedAt;
    private final boolean deleted;
    private final int countComments;

    public Article(
            @NonNull final String userId,
            @NonNull final String title,
            @NonNull final String body
    ) throws IllegalArgumentException
    {
        validate(userId, title, body);
        this.idx = 0;
        this.userId = userId;
        this.title = title.trim();
        this.body = body.trim();
        this.createdAt = Instant.now().getEpochSecond();
        this.modifiedAt = 0L;
        this.deleted = false;
        this.countComments = 0;
    }

    private void validate(
            final String userId,
            final String title,
            final String body
    )
    {
        Checker.checkString("userId", userId, User.ID_REGEX, User.ID_MIN, User.ID_MAX);
        Checker.checkString("title", title, TITLE_MIN, TITLE_MAX);
        Checker.checkString("body", body, BODY_MIN, BODY_MAX);
    }

    public long getIdx() {
        return idx;
    }

    public String getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
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

    public int getCountComments() {
        return countComments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return idx == article.idx && createdAt == article.createdAt && modifiedAt == article.modifiedAt && deleted == article.deleted && countComments == article.countComments && userId.equals(article.userId) && title.equals(article.title) && body.equals(article.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idx, userId, title, body, createdAt, modifiedAt, deleted, countComments);
    }

    @Override
    public String toString() {
        return "Article{" +
                "idx=" + idx +
                ", userId='" + userId + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                ", deleted=" + deleted +
                ", countComments=" + countComments +
                '}';
    }
}
