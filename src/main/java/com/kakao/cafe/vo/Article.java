package com.kakao.cafe.vo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class Article {
    private final Long id;
    private final String writer;

    @NotNull
    private final Long writerId;
    private final Long views;
    private final String time;

    @Size(min = 1, max = 100)
    private final String title;

    @Size(min = 1, max = 1000)
    private final String contents;


    public Article(Long id, Long writerId, String writer, String title, String contents, Long views, String time) {
        this.id = id;
        this.writerId = writerId;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.views = views;
        this.time = time;
    }

    public Long getWriterId() {
        return writerId;
    }

    public long getId() {
        return id;
    }


    public String getContents() {
        return contents;
    }


    public String getWriter() {
        return writer;
    }

    public Long getViews() {
        return views;
    }

    public String getTitle() {
        return title;
    }


    public String getTime() {
        return time;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(id, article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
