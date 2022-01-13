package com.kakao.cafe.domain;

import java.time.LocalDateTime;

public class Post {
    private int id;
    private final String userId;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;

    public Post( String userId, String title, String content) {
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
}
