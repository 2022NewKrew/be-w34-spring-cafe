package com.kakao.cafe.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Article {
    private Long id;
    private User writer;
    private String title;
    private String contents;

    public void updateId(Long id) { this.id = id; }

    public static Article of(User writer, String title, String contents) {
        return Article.builder()
                .writer(writer)
                .title(title)
                .contents(contents).build();
    }
}
