package com.kakao.cafe.domain.article;

import javax.validation.constraints.NotBlank;

public class ArticleSaveDto {
    private Long userId;
    @NotBlank(message = "제목은 공백일 수 없습니다.")
    private final String title;
    @NotBlank(message = "내용은 공백일 수 없습니다.")
    private final String content;

    public ArticleSaveDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
