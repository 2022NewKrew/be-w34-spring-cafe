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

    public static Article of(ArticleForm articleForm){
        return new Builder()
                .writerUserId(articleForm.getWriter())
                .title(articleForm.getTitle())
                .contents(articleForm.getContents())
                .registerDateTime(LocalDateTime.now())
                .build();
    }

    public static Article of(ArticleRowDataDto articleRowDataDto) {
        return new Builder()
                .id(articleRowDataDto.getId())
                .writerUserId(articleRowDataDto.getWriterUserId())
                .title(articleRowDataDto.getTitle())
                .contents(articleRowDataDto.getContents())
                .registerDateTime(articleRowDataDto.getRegisterDateTime())
                .build();
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

    private Article(Builder builder) {
        this.id = builder.id;
        this.writerUserId = builder.writerUserId;
        this.title = builder.title;
        this.contents = builder.contents;
        this.registerDateTime = builder.registerDateTime;
    }

    public static class Builder {
        private Long id;
        private String writerUserId;
        private String title;
        private String contents;
        private LocalDateTime registerDateTime;

        public Builder id(Long id){
            this.id = id;
            return this;
        }

        public Builder writerUserId(String writerUserId) {
            this.writerUserId = writerUserId;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder contents(String contents) {
            this.contents = contents;
            return this;
        }

        public Builder registerDateTime(LocalDateTime registerDateTime) {
            this.registerDateTime = registerDateTime;
            return this;
        }

        public Article build() {
            return new Article(this);
        }
    }
}
