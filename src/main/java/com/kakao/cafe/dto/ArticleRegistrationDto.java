package com.kakao.cafe.dto;

import javax.validation.constraints.NotBlank;

public class ArticleRegistrationDto {
    private final Integer articleId;
    @NotBlank(message = "게시글 제목이 null 이거나 한개의 띄어쓰기만 있습니다")
    private final String title;
    @NotBlank(message = "게시글 내용이 null 이거나 띄어쓰기만 있습니다")
    private final String content;

    public ArticleRegistrationDto(Integer articleId, String title, String content) {
        this.articleId = articleId;
        this.title = title;
        this.content = content;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
