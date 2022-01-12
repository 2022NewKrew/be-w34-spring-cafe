package com.kakao.cafe.article.domain;

import com.kakao.cafe.user.domain.User;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

import static com.kakao.cafe.common.exception.ExceptionMessage.NON_NULL_EXCEPTION;

@Getter
public class Article {

    @NonNull
    private final String id;

    @NonNull
    private final User author;

    @NonNull
    private final String title;

    @NonNull
    private final String content;

    @NonNull
    private final String createdAt;

    private Article(User user, String title, String content, String localDateTime) {
        this.id = UUID.randomUUID().toString();
        this.author = user;
        this.title = title;
        this.content = content;
        this.createdAt = localDateTime;
    }

    public static Article valueOf(User user, String title, String content) {
        validateLength(title, content);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String localDateTime = LocalDateTime.now().format(dateTimeFormatter);
        return new Article(user, title, content, localDateTime);
    }

    private static void validateLength(String title, String content) {
        if (title.trim().length() == 0 || content.trim().length() == 0) {
            throw new IllegalArgumentException(NON_NULL_EXCEPTION);
        }
    }

    public boolean isSameArticleById(String articleId) {
        return Objects.equals(this.id, articleId);
    }
}
