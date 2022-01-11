package com.kakao.cafe.dto;

import com.kakao.cafe.domain.Post;

import java.time.LocalDate;

public class PostListDto {
    private int id;
    private String title;
    private LocalDate createdAt;
    private UserDto writer;

    public PostListDto() {
    }

    public PostListDto(int id, String title, LocalDate createdAt, UserDto writer) {
        this.id = id;
        this.title = title;
        this.createdAt = createdAt;
        this.writer = writer;
    }

    public static PostListDto of(Post post) {
        return new PostListDto(post.getId(),
                post.getTitle(),
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
}
