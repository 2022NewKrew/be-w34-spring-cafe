package com.kakao.cafe.article.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kakao.cafe.user.domain.User;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
public class Article {

    private final String id;
    private final User author;
    private final String title;
    private final String content;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd' 'HH:mm", timezone = "Asia/Seoul")
    private final LocalDateTime createdAt;

    private Article(User user, String title, String content) {
        this.id = UUID.randomUUID().toString();
        this.author = user;
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }

    public static Article valueOf(User user, String title, String content) {
        return new Article(user, title, content);

    }

    public boolean isSameArticleById(String articleId) {
        return Objects.equals(this.id, articleId);
    }
}
