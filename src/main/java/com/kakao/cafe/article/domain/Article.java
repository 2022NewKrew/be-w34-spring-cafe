package com.kakao.cafe.article.domain;

import com.kakao.cafe.user.domain.User;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

import static com.kakao.cafe.common.exception.ExceptionMessage.NON_NULL_EXCEPTION;

@Getter
@Setter
@NoArgsConstructor
//@Builder
public class Article {

    @NonNull
    public int id;

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

    public Article(User user, String title, String content, String localDateTime) {
        this.authorId = user.getUserId();
        this.title = title;
        this.content = content;
        this.createdAt = localDateTime;
        this.author = user;
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
