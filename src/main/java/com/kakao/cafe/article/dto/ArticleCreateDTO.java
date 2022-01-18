package com.kakao.cafe.article.dto;

import java.util.Date;

public class ArticleCreateDTO {
    private String userId;
    private String name; //User의 name과 동일
    private String title;
    private String contents;

    public ArticleCreateDTO(String userId, String name, String title, String contents) {
        this.userId = userId;
        this.name = name;
        this.title = title;
        this.contents = contents;
    }

    public String getUserId() {
        return userId;
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
