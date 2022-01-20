package com.kakao.cafe.comment.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.kakao.cafe.common.exception.ExceptionMessage.VALUE_LENGTH_LOWERBOUND_EXCEPTION;

@Getter
@AllArgsConstructor
public class Comment {

    @Setter
    private int id;

    @NonNull
    private int articleId;

    @NonNull
    private String authorId;

    @NonNull
    private String content;

    @NonNull
    private String createdAt;

    private Comment(int articleId, String authorId, String content, String createdAt) {
        this.articleId = articleId;
        this.authorId = authorId;
        this.content = content;
        this.createdAt = createdAt;
    }

    public static Comment valueOf(int articleId, String authorId, String content) {
        validate(content);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String localDateTime = LocalDateTime.now().format(dateTimeFormatter);
        return new Comment(articleId, authorId, content, localDateTime);
    }

    /**
     * Use for JDBC mapper. This method calls All args constructor annotated above.
     */
    public static Comment valueOf(int id, int articleId, String authorId, String content, String createdAt) {
        return new Comment(id, articleId, authorId, content, createdAt);
    }

    private static void validate(String content) throws IllegalArgumentException {
        if (content.trim().length() == 0) {
            throw new IllegalArgumentException(VALUE_LENGTH_LOWERBOUND_EXCEPTION + "\nreason: content");
        }
    }
}
