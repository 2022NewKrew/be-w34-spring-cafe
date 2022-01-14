package com.kakao.cafe.thread;

import java.time.LocalDateTime;

public class Post extends Thread {
    private static final ThreadType type = ThreadType.POST;

    public Post(Long id, Long parent_id, Long author_id, String title, String content, String status,
                LocalDateTime created_at, LocalDateTime last_modified_at) {
        super(id, parent_id, author_id, title, content, status, type.name(), created_at, last_modified_at);
    }
}
