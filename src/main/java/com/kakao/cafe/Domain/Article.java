package com.kakao.cafe.Domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Getter
@Setter
public class Article {
    private Long id;
    private Timestamp created;
    private Integer views;

    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotBlank
    private String author;

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", created=" + created +
                ", views=" + views +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
