package com.kakao.cafe.domain.article;

public class ArticleVo {
    private final String writer;
    private final String title;
    private final String contents;

    public ArticleVo(String writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }
}
