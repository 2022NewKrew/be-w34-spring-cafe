package com.kakao.cafe.article.model;

public class Article {
    static private int idIndex = 1;

    private int id;
    private String user;
    private String title;
    private String content;

    public Article(int id, String user, String title, String content) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.content = content;
    }

    public Article(int id, ArticleDTO articleDTO){
        this.id = id;
        this.user = articleDTO.getUser();
        this.title = articleDTO.getTitle();
        this.content = articleDTO.getContent();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Article{" +
                ", user='" + user + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
