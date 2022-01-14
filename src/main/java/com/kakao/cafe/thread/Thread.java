package com.kakao.cafe.thread;

import java.time.LocalDateTime;

public class Thread {
    private final Long id;
    private final Long parent_id;
    private final Long author_id;
    private final String title;
    private final String content;
    private final String status;
    private final String type;
    private final LocalDateTime created_at;
    private final LocalDateTime last_modified_at;

    public Thread(Long id, Long parent_id, Long author_id, String title, String content, String status, String type,
                  LocalDateTime created_at, LocalDateTime last_modified_at) {
        this.id = id;
        this.parent_id = parent_id;
        this.author_id = author_id;
        this.title = title;
        this.content = content;
        this.status = status;
        this.type = type;
        this.created_at = created_at;
        this.last_modified_at = last_modified_at;
    }

    public Long getId() {
        return id;
    }

    public Long getParent_id() {
        return parent_id;
    }

    public Long getAuthor_id() {
        return author_id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public LocalDateTime getLast_modified_at() {
        return last_modified_at;
    }

    @Override
    public String toString() {
        return "Thread{" + "id=" + id + ", parent_id=" + parent_id + ", author_id=" + author_id + ", title='" + title + '\'' + ", content='" + content + '\'' + ", status='" + status + '\'' + ", type='" + type + '\'' + ", created_at=" + created_at + ", last_modified_at=" + last_modified_at + '}';
    }
}
