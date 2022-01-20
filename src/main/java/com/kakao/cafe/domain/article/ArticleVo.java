package com.kakao.cafe.domain.article;

import com.kakao.cafe.domain.user.User;

import java.util.Objects;

public class ArticleVo {
    private final int id;
    private final User writer;
    private final String title;
    private final String contents;

    public ArticleVo(User writer, String title, String contents) {
        this(0, writer, title, contents);
    }

    public ArticleVo(int id, User writer, String title, String contents) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public int getId() {
        return id;
    }

    public User getWriter() {
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
