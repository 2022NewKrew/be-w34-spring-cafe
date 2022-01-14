package com.kakao.cafe.model;

import lombok.Builder;
import lombok.Getter;


import java.time.LocalDateTime;



@Getter
public class Article {

    private Integer id;
    private String writer;
    private String title;
    private String contents;
    private LocalDateTime createTime;

    @Builder
    public Article(Integer id, String writer, String title, String contents, LocalDateTime createTime) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createTime = createTime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Article{" +
                "writer='" + writer + '\'' +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                '}';
    }
}
