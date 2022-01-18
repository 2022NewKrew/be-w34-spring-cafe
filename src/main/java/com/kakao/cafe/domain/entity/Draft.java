package com.kakao.cafe.domain.entity;

import java.util.Date;
import java.util.Map;

public class Draft {

    private final User author;
    private final String title;
    private final String content;

    public Draft(User author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
    }

    public Map<String, Object> toMap() {
        return Map.of(
                "owner_id", author.getId(),
                "title", title,
                "content", content
        );
    }

    public Article createArticle(long id, Date createdAt) {
        return new Article.Builder()
                .id(id)
                .author(author)
                .title(title)
                .content(content)
                .createdAt(createdAt)
                .build();
    }
}
