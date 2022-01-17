package com.kakao.cafe.domain.article;

import java.util.Objects;

public class ArticleVo {
    private final String writer;
    private final String title;
    private final String contents;

    public ArticleVo(String writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public String getWriter() {
        return writer;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleVo articleVo = (ArticleVo) o;
        return Objects.equals(writer, articleVo.writer) && Objects.equals(title, articleVo.title) && Objects.equals(contents, articleVo.contents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(writer, title, contents);
    }

    @Override
    public String toString() {
        return "ArticleVo{" +
                "writer='" + writer + '\'' +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                '}';
    }
}
