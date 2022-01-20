package com.kakao.cafe.article.application.port.in;


public class CommentResponse {
    private final String writerName;
    private final String contents;
    private final String createdTime;
    private final Long userId;
    private final Long articleId;
    private final Long commentId;

    public CommentResponse(String writerName, String contents, String createdTime, Long userId, Long articleId, Long commentId) {
        this.writerName = writerName;
        this.contents = contents;
        this.createdTime = createdTime;
        this.userId = userId;
        this.articleId = articleId;
        this.commentId = commentId;
    }
}
