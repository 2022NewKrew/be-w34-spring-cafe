package com.kakao.cafe.domain;

import com.kakao.cafe.util.Checker;
import org.springframework.lang.NonNull;

import java.time.Instant;
import java.util.Objects;

public class Article {
    public static final Article NONE = new Article();
    public static final String AUTHOR_REGEX = "[0-9a-z]+";
    public static final int AUTHOR_MIN = 6;
    public static final int AUTHOR_MAX = 12;
    public static final int TITLE_MIN = 1;
    public static final int TITLE_MAX = 255;
    public static final int BODY_MIN = 1;
    public static final int BODY_MAX = 4095;

    private static long auto_increment = 1;
    private final long idx;
    private final String author;
    private final String title;
    private final String body;
    private final long createdAt;

    public Article(
            @NonNull final String author,
            @NonNull final String title,
            @NonNull final String body
    ) throws IllegalArgumentException
    {
        validate(author, title, body);
        this.idx = auto_increment++;
        this.author = author.trim();
        this.title = title.trim();
        this.body = body.trim();
        this.createdAt = Instant.now().getEpochSecond();
    }

    private Article() {
        this.idx = 0;
        this.author = "";
        this.title = "";
        this.body = "";
        this.createdAt = Instant.now().getEpochSecond();
    }

    private void validate(
            final String author,
            final String title,
            final String body
    )
    {
        Checker.checkString("author", author, AUTHOR_REGEX,AUTHOR_MIN, AUTHOR_MAX);
        Checker.checkString("title", title, TITLE_MIN, TITLE_MAX);
        Checker.checkString("body", body, BODY_MIN, BODY_MAX);
    }

    public long getIdx() {
        return idx;
    }

    public String getAuthor() {
        return author;
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
        return idx == article.idx && author.equals(article.author) && title.equals(article.title) && body.equals(article.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idx, author, title, body);
    }

    @Override
    public String toString() {
        return "Article{" +
                "idx=" + idx +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
