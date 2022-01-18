package com.kakao.cafe.domain.post;

import lombok.*;

import java.time.LocalDateTime;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {
    private Long id;
    private Long writerId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String writerNickname;

    @Builder
    public Post(Long id, Long writerId, String title, String content, LocalDateTime createdAt, LocalDateTime updatedAt, String writerNickname) {
        this.id = id;
        this.writerId = writerId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.writerNickname = writerNickname;
    }
}
