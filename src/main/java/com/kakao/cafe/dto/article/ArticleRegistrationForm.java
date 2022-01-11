package com.kakao.cafe.dto.article;

public class ArticleRegistrationForm {
    private final String writerName;
    private final String title;
    private final String contents;

    public ArticleRegistrationForm(String writerName, String title, String contents) {
        this.writerName = writerName;
        this.title = title;
        this.contents = contents;
    }

    public String getWriterName() {
        return writerName;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }
}
