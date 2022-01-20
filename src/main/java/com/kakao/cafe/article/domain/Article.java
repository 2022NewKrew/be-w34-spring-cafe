package com.kakao.cafe.article.domain;

import com.kakao.cafe.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.kakao.cafe.common.exception.ExceptionMessage.NON_NULL_EXCEPTION;

@Getter
@AllArgsConstructor
public class Article {

    @Setter
    private int id;

    @NonNull
    private String authorId;

    @NonNull
    private String title;

    @NonNull
    private String content;

    @NonNull
    private String createdAt;

    @NonNull
    private User author;

    private Article(String userId, String title, String content, String createdAt) {
        this.authorId = userId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    public static Article valueOf(String userId, String title, String content) {
        validateLength(title, content);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String localDateTime = LocalDateTime.now().format(dateTimeFormatter);
        return new Article(userId, title, content, localDateTime);
    }

    private static void validateLength(String title, String content) {
        if (title.trim().length() == 0 || content.trim().length() == 0) {
            throw new IllegalArgumentException(NON_NULL_EXCEPTION);
        }
    }

    /**
     * Use for JDBC mapper. This method calls All args constructor annotated above.
     */
    public static Article valueOf(int id, String authorId, String title, String content, String createdAt, User author) {
        return new Article(id, authorId, title, content, createdAt, author);
    }

    public boolean isSameArticleById(int targetArticleId) {
        return this.id == targetArticleId;
    }

    public void update(String title, String contents) {
        this.title = title;
        this.content = contents;
    }
}
