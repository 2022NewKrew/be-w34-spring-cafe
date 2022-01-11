package com.kakao.cafe.model;

import java.time.LocalDateTime;
import java.util.List;

public class ArticleDTO {
    private final String writer;
    private final String title;
    private final String contents;
    private final LocalDateTime date;
    private int index;
    private int commentSize;
    private List<ArticleDTO> comments;

    public ArticleDTO(String writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.date = LocalDateTime.now();
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

    public LocalDateTime getDate() {
        return date;
    }

    public int getIndex() {
        return index;
    }

    public int getCommentSize() {
        return commentSize;
    }

    public List<ArticleDTO> getComments() {
        return comments;
    }

    public void setCommentSize(int commentSize) {
        this.commentSize = commentSize;
    }

    public void setComments(List<ArticleDTO> comments) {
        this.comments = comments;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "ArticleDTO{" +
                "writer='" + writer + '\'' +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", date=" + date +
                ", index=" + index +
                ", commentSize=" + commentSize +
                ", comments=" + comments +
                '}';
    }
}
