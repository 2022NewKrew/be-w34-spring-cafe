package com.kakao.cafe.dto.article;

public class ArticleModifyCommand {
    private String title;
    private String contents;

    public ArticleModifyCommand(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }
}
