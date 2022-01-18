package com.kakao.cafe.domain;

import lombok.Data;

@Data
public class Article {
    private Long id;
    private String writer;
    private String title;
    private String contents;

    public boolean isWriter(String curUserId) {
        return writer.equals(curUserId);
    }
}
