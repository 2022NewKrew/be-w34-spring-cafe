package com.kakao.cafe.thread;

import com.kakao.cafe.user.UserViewDTO;

import java.time.LocalDateTime;

public class PostView {
    private final Long id;
    private final UserViewDTO author;
    private final String title;
    private final String content;
    // private final CommentView commentView;
    private final LocalDateTime created_at;
    private final LocalDateTime last_modified_at;

    public PostView(Long id, UserViewDTO author, String title, String content, LocalDateTime created_at,
                    LocalDateTime last_modified_at) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
        this.created_at = created_at;
        this.last_modified_at = last_modified_at;
    }
}
