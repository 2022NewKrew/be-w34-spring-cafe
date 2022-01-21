package com.kakao.cafe.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class Reply {
    private Long id;
    private Long articleId;
    private User writer;
    private String comment;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private Boolean isDeleted;

    @Builder
    public Reply(Long id, Long articleId, User writer, String comment, LocalDateTime createdTime, LocalDateTime updatedTime, Boolean isDeleted) {
        this.id = id;
        this.articleId = articleId;
        this.writer = writer;
        this.comment = comment;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        this.isDeleted = isDeleted;
    }

    public Boolean isWriter(String userId) {
        return writer.getUserId().equals(userId);
    }

    public void updateId(Long id) {
        this.id = id;
    }

    public static Reply of(Long articleId, User writer, String comment) {
        return Reply.builder()
                .articleId(articleId)
                .writer(writer)
                .comment(comment)
                .createdTime(LocalDateTime.now())
                .build();
    }
}
