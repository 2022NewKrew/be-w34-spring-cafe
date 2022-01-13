package com.kakao.cafe.domain;

import com.kakao.cafe.util.Checker;
import org.springframework.lang.NonNull;

import java.time.Instant;
import java.util.Objects;

public class Article {
    public static final Article NONE = new Article();
    public static final int TITLE_MIN = 1;
    public static final int TITLE_MAX = 255;
    public static final int BODY_MIN = 1;
    public static final int BODY_MAX = 4095;

    private static long auto_increment = 1;
    private final long idx;
    private final String userId;
    private final String userName;
    private final String title;
    private final String body;
    private final long createdAt;

    public Article(
            @NonNull final String userId,
            @NonNull final String userName,
            @NonNull final String title,
            @NonNull final String body
    ) throws IllegalArgumentException
    {
        validate(userId, userName, title, body);
        this.idx = auto_increment++;
        this.userId = userId;
        this.userName = userName;
        this.title = title.trim();
        this.body = body.trim();
        this.createdAt = Instant.now().getEpochSecond();
    }

    private Article() {
        this.idx = 0;
        this.userId = User.NONE.getId();
        this.userName = User.NONE.getName();
        this.title = "";
        this.body = "";
        this.createdAt = Instant.now().getEpochSecond();
    }

    private void validate(
            final String userId,
            final String userName,
            final String title,
            final String body
    )
    {
        Checker.checkString("userId", userId, User.ID_REGEX, User.ID_MIN, User.ID_MAX);
        Checker.checkString("userName", userName, User.NAME_REGEX, User.NAME_MIN, User.NAME_MAX);
        Checker.checkString("title", title, TITLE_MIN, TITLE_MAX);
        Checker.checkString("body", body, BODY_MIN, BODY_MAX);
    }

    public long getIdx() {
        return idx;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
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

    public boolean isNone() {
        return this.equals(NONE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return idx == article.idx && createdAt == article.createdAt && userId.equals(article.userId) && userName.equals(article.userName) && title.equals(article.title) && body.equals(article.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idx, userId, userName, title, body, createdAt);
    }

    @Override
    public String toString() {
        return "Article{" +
                "idx=" + idx +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
