package com.kakao.cafe.dto;

import com.kakao.cafe.domain.Post;

import java.time.LocalDate;

public class PostListItemDto {
    private int id;
    private String title;
    private int viewCount;
    private LocalDate createdAt;
    private UserDto writer;

    public PostListItemDto() {
    }

    public PostListItemDto(int id, String title, int viewCount, LocalDate createdAt, UserDto writer) {
        this.id = id;
        this.title = title;
        this.viewCount = viewCount;
        this.createdAt = createdAt;
        this.writer = writer;
    }

    public static PostListItemDto of(Post post) {
        return new PostListItemDto(post.getId(),
                post.getTitle(),
                post.getViewCount(),
                post.getCreatedAt(),
                UserDto.of(post.getWriter()));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public UserDto getWriter() {
        return writer;
    }

    public void setWriter(UserDto writer) {
        this.writer = writer;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }
}
