package com.kakao.cafe.dto.reply;

import com.kakao.cafe.domain.Entity.Comment;
import lombok.Getter;

@Getter
public class DeleteCommentDto {
    private int commentId;
    private String userId;

    public DeleteCommentDto(Comment comment) {
        this.commentId = comment.getCommentId();
        this.userId = comment.getUserId();
    }
}
