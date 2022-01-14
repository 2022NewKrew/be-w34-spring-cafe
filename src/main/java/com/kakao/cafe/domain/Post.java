package com.kakao.cafe.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Post {
    private static final int INITIAL_ID = 0;
    private int id;
    private final String userId;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;

    public Post( String userId, String title, String content) {
        id = INITIAL_ID;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
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
}
