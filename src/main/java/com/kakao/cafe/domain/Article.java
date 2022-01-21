package com.kakao.cafe.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Article {
    private Long id;
    private User writer;
    private String title;
    private String contents;
    private Boolean isDeleted;

    @Builder
    public Article(Long id, User writer, String title, String contents, Boolean isDeleted) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.isDeleted = isDeleted;
    }

    public void updateId(Long id) { this.id = id; }

    public void update(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public Boolean isWriter(String userId) {
        return writer.getUserId().equals(userId);
    }

    public static Article of(User writer, String title, String contents) {
        return Article.builder()
                .writer(writer)
                .title(title)
                .contents(contents).build();
    }


}
