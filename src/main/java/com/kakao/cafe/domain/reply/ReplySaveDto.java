package com.kakao.cafe.domain.reply;

import javax.validation.constraints.NotBlank;

public class ReplySaveDto {
    private Long articleId;
    private Long userId;
    @NotBlank(message = "댓글은 공백일 수 없습니다.")
    private final String content;

    public ReplySaveDto(String content) {
        this.content = content;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }
}
