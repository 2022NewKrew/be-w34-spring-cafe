package com.kakao.cafe.comment.application.dto;

import com.kakao.cafe.comment.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
public class CommentSaveRequest {

    public final String contents;

    public Comment toComment(int articleId, String authorId) {
        return Comment.valueOf(articleId, authorId, contents);
    }
}
