package com.kakao.cafe.domain.article.dto;

import com.kakao.cafe.domain.article.Article;

import java.time.LocalDateTime;

// DB에 저장된 데이터 (writer - Article(string), ArticleRowDataDto(Long))
public class ArticleRowDataDto {

    private Long id;
    private Long writerId;
    private String writerUserId;
    private String title;
    private String contents;
    private LocalDateTime registerDateTime;

    public ArticleRowDataDto() {}

    public ArticleRowDataDto(Long id, Long writerId, String writerUserId, String title, String contents, LocalDateTime registerDateTime) {
        this.id = id;
        this.writerId = writerId;
        this.writerUserId = writerUserId;
        this.title = title;
        this.contents = contents;
        this.registerDateTime = registerDateTime;
    }

    public static ArticleRowDataDto from(Article article, Long writerId) {
        return new ArticleRowDataDto(article.getId(), writerId, article.getWriterUserId(), article.getTitle(), article.getContents(), article.getRegisterDateTime());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWriterId() {
        return writerId;
    }

    public void setWriterId(Long writerId) {
        this.writerId = writerId;
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

    public LocalDateTime getRegisterDateTime() {
        return registerDateTime;
    }

    public void setRegisterDateTime(LocalDateTime registerDateTime) {
        this.registerDateTime = registerDateTime;
    }

    public String getWriterUserId() {
        return writerUserId;
    }

    public void setWriterUserId(String writerUserId) {
        this.writerUserId = writerUserId;
    }
}
