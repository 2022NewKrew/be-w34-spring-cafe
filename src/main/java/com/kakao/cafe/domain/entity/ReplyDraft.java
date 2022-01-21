package com.kakao.cafe.domain.entity;

import java.util.Date;
import java.util.Map;

public class ReplyDraft {

    private final Article target;
    private final User author;
    private final String content;

    public ReplyDraft(Article target, User author, String content) {
        this.target = target;
        this.author = author;
        this.content = content;
    }

    public Map<String, Object> toMap() {
        return Map.of(
                "author_id", author.getId(),
                "target_id", target.getId(),
                "content", content
        );
    }

    public Reply createReply(long id, Date createdAt) {
        return new Reply.Builder()
                .id(id)
                .author(author)
                .target(target)
                .content(content)
                .createdAt(createdAt)
                .build();
    }
}
