package com.kakao.cafe.domain.entity;

import java.util.Date;
import java.util.Map;

public class Draft {

    private final User owner;
    private final String author;
    private final String title;
    private final String content;

    public Draft(User owner, String author, String title, String content) {
        this.owner = owner;
        this.author = author;
        this.title = title;
        this.content = content;
    }

    public Map<String, ?> toMap() {
        return Map.of(
                "owner_id", owner.getId(),
                "author", author,
                "title", title,
                "content", content
        );
    }

    public Article createArticle(long id, Date createdAt) {
        return new Article.Builder()
                .id(id)
                .owner(owner)
                .author(author)
                .title(title)
                .content(content)
                .createdAt(createdAt)
                .build();
    }
}
