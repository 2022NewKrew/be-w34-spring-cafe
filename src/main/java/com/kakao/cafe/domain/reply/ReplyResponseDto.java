package com.kakao.cafe.domain.reply;

import com.kakao.cafe.domain.user.User;

import java.time.format.DateTimeFormatter;

public class ReplyResponseDto {
    private final Long id;
    private final String writer;
    private final String content;
    private final String creationTime;

    public ReplyResponseDto(Reply reply, User user) {
        this.id = reply.getId();
        this.writer = user == null ? "삭제된 유저" : user.getName();
        this.content = reply.getContent();
        this.creationTime = reply.getCreationTime().format(DateTimeFormatter.ISO_DATE);
    }

    public Long getId() {
        return id;
    }

    public String getWriter() {
        return writer;
    }

    public String getContent() {
        return content;
    }

    public String getCreationTime() {
        return creationTime;
    }
}
