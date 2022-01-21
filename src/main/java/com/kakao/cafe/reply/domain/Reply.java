package com.kakao.cafe.reply.domain;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class Reply {

    private Long id;
    private Long userPK;
    private Long articlePK;
    private String writer;
    private String contents;
    private String writingTime;

    public Reply(Long id, Long userPK, Long articlePK, String writer, String contents, String writingTime) {
        this.id = id;
        this.userPK = userPK;
        this.articlePK = articlePK;
        this.writer = writer;
        this.contents = contents;
        this.writingTime = writingTime;
    }

    public Reply(Long articlePK, Long userPK, String writer, String contents) {
        this.articlePK = articlePK;
        this.userPK = userPK;
        this.writer = writer;
        this.contents = contents;
        this.writingTime = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm"));
    }
}
