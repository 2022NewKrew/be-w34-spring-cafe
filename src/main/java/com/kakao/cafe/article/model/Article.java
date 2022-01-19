package com.kakao.cafe.article.model;

import com.kakao.cafe.common.BaseEntity;

import javax.validation.constraints.NotEmpty;

public class Article extends BaseEntity {
    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
