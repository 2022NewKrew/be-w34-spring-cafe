package com.kakao.cafe.model.vo;

import java.util.Objects;

public class ArticleVo {

    private int id;
    private UserVo writer;
    private String title;
    private String contents;

    public ArticleVo() {

    }

    public ArticleVo(UserVo writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public ArticleVo(int id, UserVo writer, String title, String contents) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public int getId() {
        return id;
    }

    public UserVo getWriter() {
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
        return id == articleVo.id && Objects.equals(writer, articleVo.writer) && Objects.equals(title, articleVo.title) && Objects.equals(contents, articleVo.contents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, writer, title, contents);
    }
}
