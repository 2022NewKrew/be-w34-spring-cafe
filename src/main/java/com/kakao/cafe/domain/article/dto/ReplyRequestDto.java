package com.kakao.cafe.domain.article.dto;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.reply.Reply;

public class ReplyRequestDto {
    private Long articleId;
    private String userId;
    private String comment;

    public Reply toReply(Article article) {
        return new Reply(article.getId(), userId, comment);
    }

    public Long getArticleId() {
        return articleId;
    }

    public String getComment() {
        return comment;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
