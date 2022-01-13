package com.kakao.cafe.domain.article;

import com.kakao.cafe.domain.article.dto.ArticleForm;
import com.kakao.cafe.domain.article.dto.ArticleRowDataDto;

import java.time.LocalDateTime;
import java.util.Objects;

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

    public static Article of(ArticleRowDataDto articleRowDataDto, String writerUserId) {
        return new Article(articleRowDataDto.getId(), writerUserId, articleRowDataDto.getTitle(), articleRowDataDto.getContents(), articleRowDataDto.getRegisterDateTime());
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(id, article.id) &&
                Objects.equals(writerUserId, article.writerUserId) &&
                Objects.equals(title, article.title) && Objects.equals(contents, article.contents) &&
                Objects.equals(registerDateTime, article.registerDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, writerUserId, title, contents, registerDateTime);
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", writerUserId='" + writerUserId + '\'' +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", registerDateTime=" + registerDateTime +
                '}';
    }
}
