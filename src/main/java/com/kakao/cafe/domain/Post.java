package com.kakao.cafe.domain;

import java.util.Date;
import java.util.Objects;

public class Post {

    private final int id;
    private final int userId;
    private final String title;
    private final String content;
    private final Date createdAt;

    public Post(int id, int userId, String title, String content, Date createdAt) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post that = (Post) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Post id: " + id +
                ", userId: " + userId +
                ", title: " + title +
                ", content: " + content +
                ", createdAt: " + createdAt;
    }
}
