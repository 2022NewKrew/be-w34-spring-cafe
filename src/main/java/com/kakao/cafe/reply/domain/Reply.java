package com.kakao.cafe.reply.domain;

import com.kakao.cafe.exception.AccessDeniedException;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class Reply {

    private Long id;
    private Long userFK;
    private Long articleFK;
    private String writer;
    private String contents;
    private String writingTime;

    public Reply(Long id, Long userFK, Long articleFK, String writer, String contents, String writingTime) {
        this.id = id;
        this.userFK = userFK;
        this.articleFK = articleFK;
        this.writer = writer;
        this.contents = contents;
        this.writingTime = writingTime;
    }

    public Reply(Long articleFK, Long userFK, String writer, String contents) {
        this.articleFK = articleFK;
        this.userFK = userFK;
        this.writer = writer;
        this.contents = contents;
        this.writingTime = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm"));
    }

    public void validateAccessDeleteReply(Long userFK) {
        if (userFK != this.userFK) {
            throw new AccessDeniedException("해당 댓글을 삭제 할 수 있는 권한이 없습니다.");
        }
    }
}
