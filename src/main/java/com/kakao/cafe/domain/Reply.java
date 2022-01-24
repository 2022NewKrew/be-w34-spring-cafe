package com.kakao.cafe.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reply {
    long id;
    long userId;
    long articleId;
    String comments;
    LocalDateTime createdAt;

    public Reply(long userId, long articleId, String comments, LocalDateTime createdAt) {
        this.userId = userId;
        this.articleId = articleId;
        this.comments = comments;
        this.createdAt = createdAt;
    }
}
