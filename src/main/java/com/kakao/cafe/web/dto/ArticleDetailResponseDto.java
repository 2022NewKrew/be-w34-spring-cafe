package com.kakao.cafe.web.dto;

import com.kakao.cafe.domain.article.Article;

import java.util.Objects;

public class ArticleDetailResponseDto {

    private Long id;
    private String writer;
    private String title;
    private String contents;

    private ArticleDetailResponseDto(Long id, String writer, String title, String contents) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public static ArticleDetailResponseDto from(Article article) {
        return new ArticleDetailResponseDto(article.getId(), article.getWriter(), article.getTitle(), article.getContents());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleDetailResponseDto that = (ArticleDetailResponseDto) o;
        return Objects.equals(id, that.id) && Objects.equals(writer, that.writer) && Objects.equals(title, that.title) && Objects.equals(contents, that.contents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, writer, title, contents);
    }
}
