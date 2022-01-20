package com.kakao.cafe.thread.dto;

import com.kakao.cafe.user.dto.UserView;

import java.time.LocalDateTime;

public class PostView {
    private final Long id;
    private final UserView author;
    private final String title;
    private final String content;
    // private final CommentView commentView;
    private final LocalDateTime createdAt;
    private final LocalDateTime lastModifiedAt;

    public PostView(Long id, UserView author, String title, String content, LocalDateTime createdAt,
                    LocalDateTime lastModifiedAt) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.lastModifiedAt = lastModifiedAt;
    }
}
