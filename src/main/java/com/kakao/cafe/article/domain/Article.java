package com.kakao.cafe.article.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kakao.cafe.user.domain.User;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class Article {
    private final User author;
    private final String title;
    private final String content;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private final LocalDateTime createdAt;

    public Article(User user, String title, String content) {
        this.author = user;
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }

    public static Article valueOf(User user, String title, String content) {
        return new Article(user, title, content);

    }
}
