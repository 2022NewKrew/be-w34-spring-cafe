package com.kakao.cafe.comment.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class Comment {

    private final Long commentId;
    private final String content;
    private final LocalDateTime createdAt;
    private final Long questionPostId;
    private final Long userAccountId;

    @Builder
    public Comment(Long commentId, String content, LocalDateTime createdAt, Long questionPostId, Long userAccountId) {
        this.commentId = commentId;
        this.content = content;
        this.createdAt = createdAt;
        this.questionPostId = questionPostId;
        this.userAccountId = userAccountId;
    }
}
