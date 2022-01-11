package com.kakao.cafe.domain;

import com.kakao.cafe.dto.PostCreateDto;

import java.time.LocalDate;

public class Post {
    // 다음 생성하는 row 의 id 값을 저장하고 있는 변수
    private static int postSeq = 1;
    private int id;
    private String title;
    private String content;
    private LocalDate createdAt;
    private User writer;
    private int viewCount;

    public Post() {
    }

    public Post(int id, String title, String content, LocalDate createdAt, User writer, int viewCount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.writer = writer;
        this.viewCount = viewCount;
    }

    public static Post of(PostCreateDto postCreateDto, User user) {
        return new Post(postSeq++,
                postCreateDto.getTitle(),
                postCreateDto.getContents(),
                LocalDate.now(),
                user,
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

    public User getWriter() {
        return writer;
    }

    public void setWriter(User writer) {
        this.writer = writer;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public void updateViewCount() {
        this.viewCount ++;
    }
}
