package com.kakao.cafe.model.comment;

import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentDto {
    private long id;
    private long postId;
    private long writerId;
    private String content;
    private LocalDateTime createdAt;
    private String writerNickname;

    @Builder
    public CommentDto(long id, long postId, long writerId, String content, LocalDateTime createdAt, String writerNickname) {
        this.id = id;
        this.postId = postId;
        this.writerId = writerId;
        this.content = content;
        this.createdAt = createdAt;
        this.writerNickname = writerNickname;
    }

    public String getFormattedCreatedAt() {
        return createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

}
