package com.kakao.cafe.dto.post;

import com.kakao.cafe.domain.Post;
import com.kakao.cafe.dto.member.MemberDto;

import java.time.LocalDate;

public class PostDetailDto {
    private int id;
    private String title;
    private String contents;
    private LocalDate createdAt;
    private int viewCount;

    private MemberDto writer;

    public PostDetailDto() {
    }

    public PostDetailDto(int id, String title, String contents, LocalDate createdAt, int viewCount, MemberDto writer) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
        this.viewCount = viewCount;
        this.writer = writer;
    }

    public static PostDetailDto of(Post post) {
        return new PostDetailDto(post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getCreatedAt(),
                post.getViewCount(),
                MemberDto.of(post.getWriter()));
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

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public MemberDto getWriter() {
        return writer;
    }

    public void setWriter(MemberDto writer) {
        this.writer = writer;
    }
}
