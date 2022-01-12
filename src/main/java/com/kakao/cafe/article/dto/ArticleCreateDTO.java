package com.kakao.cafe.article.dto;

import java.util.Date;

public class ArticleCreateDTO {
    private String name; //User의 name과 동일
    private String title;
    private String contents;

    public ArticleCreateDTO(String name, String title, String contents) {
        this.name = name;
        this.title = title;
        this.contents = contents;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }
}
