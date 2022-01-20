package com.kakao.cafe.model.DTO;

public class ArticleDTO {
    private String user;
    private String title;
    private String content;

    public ArticleDTO(String user, String title, String content) {
        this.user = user;
        this.title = title;
        this.content = content;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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
