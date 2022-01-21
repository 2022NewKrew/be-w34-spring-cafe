package com.kakao.cafe.dto.reply;

import com.kakao.cafe.domain.Entity.Comment;
import lombok.Getter;

@Getter
public class ShowCommentDto {
    private int commentId;
    private int articleId;
    private String writer;
    private String contents;

    public ShowCommentDto(Comment comment) {
        this.commentId = comment.getCommentId();
        this.articleId = comment.getArticleId();
        this.writer = comment.getWriter();
        this.contents = comment.getContents();
    }
}
