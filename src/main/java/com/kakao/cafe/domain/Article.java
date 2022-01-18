package com.kakao.cafe.domain;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class Article {

    private Long id;
    private User writer;
    private String title;
    private String contents;

    public void setId(long id) {
        this.id = id;
    }

    public void setWriter(User writer) {
        this.writer = writer;
    }

    public void update(String updateTitle, String updateContents) {
        title = updateTitle;
        contents = updateContents;
    }
}
