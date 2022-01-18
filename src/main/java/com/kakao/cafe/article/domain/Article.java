package com.kakao.cafe.article.domain;

import com.kakao.cafe.user.domain.User;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Article {
    @NotNull
    private final Long id;

    @NotBlank
    private final String title;

    @NotBlank
    private final String content;

    @NotNull
    private final User user;

    @NotNull
    private Long viewNum;

    @NotNull
    private final LocalDateTime createdDate;

    public Article(Long id, String title, String content, User user, Long viewNum, LocalDateTime createdDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
        this.viewNum = viewNum;
        this.createdDate = createdDate;
    }

    public void incrementViewNum(){
        this.viewNum++;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public User getUser() {
        return user;
    }

    public Long getViewNum() {
        return viewNum;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
}
