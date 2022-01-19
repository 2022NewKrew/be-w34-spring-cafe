package com.kakao.cafe.article.model;

import com.kakao.cafe.common.BaseEntity;
import com.kakao.cafe.user.model.User;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Article extends BaseEntity {
    @NotNull
    private User author;

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

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
