package com.kakao.cafe.qna.comment;

import lombok.Data;

import java.time.format.DateTimeFormatter;

/**
 * Created by melodist
 * Date: 2022-01-20 020
 * Time: 오전 12:36
 */
@Data
public class CommentDto {

    private Integer id;
    private Integer writerId;

    private String writer;
    private String contents;
    private String modifiedDate;

    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.writerId = comment.getWriterId();

        this.writer = comment.getWriter();
        this.contents = comment.getContents();
        this.modifiedDate = comment.getModifiedDate()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
