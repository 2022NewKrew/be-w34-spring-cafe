package com.kakao.cafe.domain;

import com.kakao.cafe.dto.reply.ReplyCreateDto;

import java.time.LocalDateTime;

public class Reply {
    private int id;
    private Post post;
    private Member writer;
    private String content;
    private LocalDateTime createdAt;
    private boolean isRemoved;

    public Reply() {
    }

    public Reply(int id, Post post, Member writer, String content, LocalDateTime createdAt, boolean isRemoved) {
        this.id = id;
        this.post = post;
        this.writer = writer;
        this.content = content;
        this.createdAt = createdAt;
        this.isRemoved = isRemoved;
    }

    public Reply(Post post, Member writer, String content, LocalDateTime createdAt, boolean isRemoved) {
        this.post = post;
        this.writer = writer;
        this.content = content;
        this.createdAt = createdAt;
        this.isRemoved = isRemoved;
    }

    public static Reply of(ReplyCreateDto replyCreateDto, Post post, Member member) {
        return new Reply(
                post,
                member,
                replyCreateDto.getContents(),
                LocalDateTime.now(),
                false
        );
    }

    public int getId() {
        return id;
    }

    public Post getPost() {
        return post;
    }

    public Member getWriter() {
        return writer;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean isRemoved() {
        return isRemoved;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public void setWriter(Member writer) {
        this.writer = writer;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setRemoved(boolean removed) {
        isRemoved = removed;
    }
}
