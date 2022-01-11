package com.kakao.cafe.entiry;

import com.kakao.cafe.dto.ArticleForm;

import java.time.LocalDateTime;

public class Article {

    private Long id;
    private String writerUserId;
    private String title;
    private String contents;
    private LocalDateTime registerDateTime;

    public Article() {}

    public Article(String writerUserId, String title, String contents) {
        this.writerUserId = writerUserId;
        this.title = title;
        this.contents = contents;
        this.registerDateTime = LocalDateTime.now();
    }

    public Article(Long id, String writerUserId, String title, String contents, LocalDateTime registerDateTime) {
        this.id = id;
        this.writerUserId = writerUserId;
        this.title = title;
        this.contents = contents;
        this.registerDateTime = registerDateTime;
    }

    public static Article of(ArticleForm articleForm){
        return new Article(articleForm.getWriter(), articleForm.getTitle(), articleForm.getContents());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWriterUserId() {
        return writerUserId;
    }

    public void setWriterUserId(String writerUserId) {
        this.writerUserId = writerUserId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public LocalDateTime getRegisterDateTime() {
        return registerDateTime;
    }

    public void setRegisterDateTime(LocalDateTime registerDateTime) {
        this.registerDateTime = registerDateTime;
    }
}
