package com.kakao.cafe.domain.comment;

import lombok.*;

import java.time.LocalDateTime;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
    private long id;
    private long postId;
    private long writerId;
    private String content;
    private LocalDateTime createdAt;

    private String writerNickname;

    private boolean hasAuthority;

    @Builder
    public Comment(long id, long postId, long writerId, String content, LocalDateTime createdAt, String writerNickname) {
        this.id = id;
        this.postId = postId;
        this.writerId = writerId;
        this.content = content;
        this.createdAt = createdAt;
        this.writerNickname = writerNickname;
    }

    public void checkAuthority(long currentId) {
        hasAuthority = writerId == currentId;
    }

}
