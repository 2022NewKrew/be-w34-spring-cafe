package com.kakao.cafe.domain.comment;

import com.kakao.cafe.domain.article.dto.ArticleAndCommentRawDataDto;
import lombok.Builder;

import java.time.LocalDateTime;

public class Comment {

    private Long id;
    private Long writerId;
    private String writerUserId;
    private String articleUserId;
    private String contents;
    private LocalDateTime registerDateTime;

    @Builder
    public Comment(long id, String articleUserId, String writerUserId, String contents, LocalDateTime registerDateTime) {
        this.id = id;
        this.articleUserId = articleUserId;
        this.writerUserId = writerUserId;
        this.contents = contents;
        this.registerDateTime = registerDateTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getArticleUserId() {
        return articleUserId;
    }

    public void setArticleUserId(String articleUserId) {
        this.articleUserId = articleUserId;
    }

    public String getWriterUserId() {
        return writerUserId;
    }

    public void setWriterUserId(String writerUserId) {
        this.writerUserId = writerUserId;
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

    public static Comment from(ArticleAndCommentRawDataDto dto) {
        return Comment.builder()
                .id(dto.getCommentId())
                .articleUserId(dto.getAuthorUserId())
                .writerUserId(dto.getCommenterUserId())
                .contents(dto.getComment())
                .registerDateTime(dto.getCommentTime())
                .build();
    }

    public static boolean checkNull(ArticleAndCommentRawDataDto dto) {
        return dto.getComment() != null;
    }
}
