package com.kakao.cafe.domain.comment.dto;

import java.time.LocalDateTime;

public class CommentRawDataDto {

    private long id;
    private Long articleId;
    private Long writerLongId;
    private String contents;
    private LocalDateTime registerDateTime;

    public CommentRawDataDto() {}

    public CommentRawDataDto(Long articleId, Long writerLongId, String contents) {
        this.articleId = articleId;
        this.writerLongId = writerLongId;
        this.contents = contents;
    }

    public Long getArticleId() {
        return articleId;
    }

    public Long getWriterLongId() {
        return writerLongId;
    }

    public String getContents() {
        return contents;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public void setWriterLongId(Long writerLongId) {
        this.writerLongId = writerLongId;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getRegisterDateTime() {
        return registerDateTime;
    }

    public void setRegisterDateTime(LocalDateTime registerDateTime) {
        this.registerDateTime = registerDateTime;
    }
}
