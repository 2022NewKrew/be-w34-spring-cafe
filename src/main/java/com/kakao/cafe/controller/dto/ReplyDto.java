package com.kakao.cafe.controller.dto;

import com.kakao.cafe.domain.Reply;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class ReplyDto {

    private int id;
    private String writer;
    private String content;
    private LocalDateTime date;
    private int articleId;


    private ReplyDto(int id, String writer, String content, LocalDateTime date, int articleId) {
        this.id = id;
        this.writer = writer;
        this.content = content;
        this.date = date;
        this.articleId = articleId;
    }

    private ReplyDto() {
    }

    public static ReplyDto from(Reply reply) {
        return new ReplyDto(reply.getId(), reply.getWriter(), reply.getContent(), reply.getDate(), reply.getArticleId());
    }

    public static Reply toEntity(ReplyDto replyDto) {
        return new Reply(replyDto.getWriter(), replyDto.getContent(), LocalDateTime.now(), replyDto.articleId);
    }

    @Override
    public String toString() {
        return String.format(
                "ReplyDto{id=%d, writer=%s, content=%s, date=%s, articleId=%d}",
                id,
                writer,
                content,
                date,
                articleId);
    }
}
