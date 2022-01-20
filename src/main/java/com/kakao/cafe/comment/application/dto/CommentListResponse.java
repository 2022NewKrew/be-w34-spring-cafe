package com.kakao.cafe.comment.application.dto;

import com.kakao.cafe.comment.domain.Comment;
import lombok.Builder;

@Builder
public class CommentListResponse {

    public final int commentId;
    public final int articleId;
    public final String authorId;
    public final String content;
    public final String createdAt;

    public static CommentListResponse valueOf(Comment comment) {
        return CommentListResponse.builder()
                .commentId(comment.getId())
                .articleId(comment.getArticleId())
                .authorId(comment.getAuthorId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .build();
    }
}
