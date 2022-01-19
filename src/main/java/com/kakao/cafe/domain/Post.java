package com.kakao.cafe.domain;

import com.kakao.cafe.dto.PostCreateDto;

import java.time.LocalDate;

public class Post {
    private int id;
    private String title;
    private String content;
    private LocalDate createdAt;
    private Member writer;
    private int viewCount;

    public Post() {
    }

    public Post(String title, String content, LocalDate createdAt, Member writer, int viewCount) {
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.writer = writer;
        this.viewCount = viewCount;
    }

    public Post(int id, String title, String content, LocalDate createdAt, Member writer, int viewCount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.writer = writer;
        this.viewCount = viewCount;
    }

    public static Post of(PostCreateDto postCreateDto, Member member) {
        return new Post(postCreateDto.getTitle(),
                postCreateDto.getContents(),
                LocalDate.now(),
                member,
                0);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Member getWriter() {
        return writer;
    }

    public void setWriter(Member writer) {
        this.writer = writer;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public void updateViewCount() {
        this.viewCount++;
    }
}
