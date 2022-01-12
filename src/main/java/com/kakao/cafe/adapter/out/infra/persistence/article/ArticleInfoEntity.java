package com.kakao.cafe.adapter.out.infra.persistence.article;

import com.kakao.cafe.application.article.dto.WriteRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ArticleInfoEntity {

    private final int id;
    private final String writer;
    private final String title;
    private final String contents;
    private final String createdAt;

    public ArticleInfoEntity(int id, String writer, String title, String contents, String createdAt) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
    }

    static ArticleInfoEntity from(int id, WriteRequest writeRequest) {
        return new ArticleInfoEntity(
            id,
            writeRequest.getWriter(),
            writeRequest.getTitle(),
            writeRequest.getContents(),
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
        );
    }

    public int getId() {
        return id;
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

    public String getCreatedAt() {
        return createdAt;
    }
}
