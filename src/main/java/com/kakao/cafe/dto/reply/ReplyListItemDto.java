package com.kakao.cafe.dto.reply;

import com.kakao.cafe.domain.Reply;
import com.kakao.cafe.dto.member.MemberDto;

import java.time.LocalDateTime;

public class ReplyListItemDto {
    private int id;
    private String content;
    private LocalDateTime createdAt;
    private MemberDto writer;

    public ReplyListItemDto() {
    }

    public ReplyListItemDto(int id, String content, LocalDateTime createdAt, MemberDto writer) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.writer = writer;
    }

    public static ReplyListItemDto of(Reply reply) {
        return new ReplyListItemDto(reply.getId(), reply.getContent(), reply.getCreatedAt(), MemberDto.of(reply.getWriter()));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public MemberDto getWriter() {
        return writer;
    }

    public void setWriter(MemberDto writer) {
        this.writer = writer;
    }
}
